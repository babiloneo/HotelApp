package itesloscabos.com.hotelapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.Realm;
import itesloscabos.com.hotelapp.Models.AppState;
import itesloscabos.com.hotelapp.Models.HoldSell.Contact;
import itesloscabos.com.hotelapp.Models.HoldSell.Customer;
import itesloscabos.com.hotelapp.Models.HoldSell.GetHold;
import itesloscabos.com.hotelapp.Models.HoldSell.GetPayment;
import itesloscabos.com.hotelapp.Models.HoldSell.GetSell;
import itesloscabos.com.hotelapp.Models.HoldSell.GuestList;
import itesloscabos.com.hotelapp.Models.HoldSell.Hold;
import itesloscabos.com.hotelapp.Models.HoldSell.Payment;
import itesloscabos.com.hotelapp.Models.HoldSell.Product;
import itesloscabos.com.hotelapp.Models.HoldSell.ResultHold;
import itesloscabos.com.hotelapp.Models.HoldSell.ResultPayment;
import itesloscabos.com.hotelapp.Models.HoldSell.ResultSell;
import itesloscabos.com.hotelapp.cliente.clients;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionPagoActivity extends AppCompatActivity {
    Button confirmar;
    private static final String TAG = "PRUEBA";

    String total,RuleTransactionId,state,city,country,zipCode;
    float taxRate;
    TextView subTotal,iva,totalB;
    EditText t_nombre,t_numero,t_expira,t_ccv;
    EditText d_nombre,d_apellidos,d_correo,d_telefono,d_pais,d_peticion;

    Hold nuevo;
    String habitacion;

    String statusx="0";
    String referenciax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_pago);
        obtenerDatosIntent();
        obtenerIDS();
        setearValores();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView Regre=(ImageView)findViewById(R.id.RGinfoPago);
        Regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       confirmar = (Button)findViewById(R.id.Complete);
        irFinal();


    }


    public void MetodoSell() {

        clients.getInstance().getService().getSell(nuevo,"Bearer "+AppState.accessToken,AppState.Environment).enqueue(new Callback<GetSell>() {
            @Override
            public void onResponse(Call<GetSell> call, Response<GetSell> response) {

                if(response.isSuccessful()){


                        List<ResultSell>resultSells=response.body().getResult();
                        if(resultSells!=null){
                            for(int x=0;x<resultSells.size();x++){
                                ResultSell p = resultSells.get(x);
                                referenciax =p.getProviderReference();
                                statusx=p.getDetail().getStatus();
                                Log.e(TAG, "sell: "+referenciax+" ststus: "+statusx);
                            }
                        }

                    if(statusx.equals("1")){
                        termina();
                    }

                }else{

                    switch(response.code()){
                        case 400:
                            Log.e(TAG, "Server Return Error: no se: "+response.errorBody());
                            break;
                        case 404:
                            Log.e(TAG, "Server Return Error: Not Faund "+response.errorBody());
                            break;
                        case 500:
                            Log.e(TAG, "Server Return Error: Server Is Broken: "+response.errorBody());
                            break;
                        default:
                            Log.e(TAG, "Server Return Error: Unknown Error: "+response.errorBody());
                            break;
                    }

                }

            }

            @Override
            public void onFailure(Call<GetSell> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void MetodoDePagoPayment() {

        Customer customer= new Customer(
                "",
                d_nombre.getText().toString(),
                d_apellidos.getText().toString(),
                d_correo.getText().toString(),
                d_telefono.getText().toString()
        );

        Product product = new Product(
                "Cuarto de hotel",
                totalB.getText().toString(),
                0
        );

        Payment payment =new Payment(
                "1",
                "",
                "https://www.google.com.mx/?gfe_rd=cr&ei=oUdVWb-aJ-KcXpGLlrAH",
                "",
                customer,
                product,
                "",
                t_numero.getText().toString(),
                t_expira.getText().toString(),
                t_ccv.getText().toString(),
                "DUM",
                "",
                RuleTransactionId,
                "0",
                "",
                ""
        );

        clients.getInstance().getService().getPayment(payment,"Bearer "+AppState.accessToken,AppState.Environment).enqueue(new Callback<GetPayment>() {
            @Override
            public void onResponse(Call<GetPayment> call, Response<GetPayment> response) {

                if(response.isSuccessful()){

                        int mode;
                        int metodo;
                        String referencias;
                        int status=0;
                        List<ResultPayment> resultPayments=response.body().getResult();
                        if(resultPayments!=null){

                            for(int x=0;x<resultPayments.size();x++){
                                ResultPayment p = resultPayments.get(x);
                                mode =p.getMode();
                                metodo =p.getMethod();
                                referencias =p.getReference();
                                status =p.getStatus();
                                Log.e(TAG, "Payment: "+mode+" "+metodo+" "+referencias+" "+status);

                            }
                        }

                        if(status==1){
                            MetodoSell();
                        }

                }else{

                    switch(response.code()){
                        case 400:
                            Log.e(TAG, "Server Return Error: no se: "+response.errorBody());
                            break;
                        case 404:
                            Log.e(TAG, "Server Return Error: Not Faund "+response.errorBody());
                            break;
                        case 500:
                            Log.e(TAG, "Server Return Error: Server Is Broken: "+response.errorBody());
                            break;
                        default:
                            Log.e(TAG, "Server Return Error: Unknown Error: "+response.errorBody());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPayment> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private boolean PonerEnHold() {
        statusx="0";
        Log.e(TAG, "checando: "+country+" "+city+" "+state+" "+zipCode);

        List<GuestList>guestLists=new ArrayList<GuestList>();
        GuestList lista=new GuestList();
        lista.setFirstName(d_nombre.getText().toString());
        lista.setLastName(d_apellidos.getText().toString());
        lista.setAddress("Lomas Del Sol");
        lista.setHomePhone(d_telefono.getText().toString());
        lista.setOfficePhone(d_telefono.getText().toString());
        lista.setMobilePhone(d_telefono.getText().toString());
        lista.setWorkPhone(d_telefono.getText().toString());
        lista.setEmail(d_correo.getText().toString());
        lista.setSpecialRequest(d_peticion.getText().toString());
        lista.setCountry(country);
        lista.setState(state);
        lista.setCity(city);
        lista.setZipCode(zipCode);
        lista.setIsChildren(false);
        lista.setAge(0);
        guestLists.add(lista);

        Contact contacto=new Contact(
                d_nombre.getText().toString(),
                d_apellidos.getText().toString(),
                "Lomas Del Sol",
                d_telefono.getText().toString(),
                d_telefono.getText().toString(),
                d_telefono.getText().toString(),
                d_telefono.getText().toString(),
                d_correo.getText().toString(),
                d_peticion.getText().toString(),
                country,
                state,
                city,
                zipCode,
                false,
                0
        );


        nuevo=new Hold(
                true,
                guestLists,
                contacto,
                "",
                "",
                true,
                RuleTransactionId,
                "",
                "",
                ""
        );

        clients.getInstance().getService().getHold(nuevo,"Bearer " + AppState.accessToken,"TEST").enqueue(new Callback<GetHold>() {

            @Override
            public void onResponse(Call<GetHold> call, Response<GetHold> response) {

                if(response.isSuccessful()){
                    String reference="";
                    List<ResultHold> result=response.body().getResult();
                        if(result!=null){

                            for(int x=0;x<result.size();x++){
                                ResultHold p =result.get(x);
                                reference=p.getProviderReference();
                                Log.e(TAG, "refencia Hold: "+reference);
                            }
                        }
                    if(reference!=""){
                        MetodoDePagoPayment();
                    }
                }else{
                    switch(response.code()){
                        case 400:
                            Log.e(TAG, "Server Return Error: no se: "+response.errorBody());
                            break;
                        case 404:
                            Log.e(TAG, "Server Return Error: Not Faund "+response.errorBody());
                            break;
                        case 500:
                            Log.e(TAG, "Server Return Error: Server Is Broken: "+response.errorBody());
                            break;
                        default:
                            Log.e(TAG, "Server Return Error: Unknown Error: "+response.errorBody());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GetHold> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });


        return true;
    }

    private void setearValores() {
        subTotal.setText(total);
        if(taxRate==0){
            iva.setText("IVA Incluido");
            totalB.setText(total);
        }else{
            iva.setText(""+iva);
            float aux=Float.parseFloat(total);
            float totalF=aux+taxRate;
            totalB.setText(""+totalF);
        }
    }

    private void obtenerIDS() {

        subTotal=(TextView)findViewById(R.id.pag_subtotal);
        iva=(TextView)findViewById(R.id.pag_iva);
        totalB=(TextView)findViewById(R.id.pag_total);

        t_nombre=(EditText)findViewById(R.id.tar_nombre);
        t_numero=(EditText)findViewById(R.id.tar_numero);
        t_expira=(EditText)findViewById(R.id.tar_expira);
        t_ccv=(EditText)findViewById(R.id.tar_ccv);

        d_nombre=(EditText)findViewById(R.id.dat_nombre);
        d_apellidos=(EditText)findViewById(R.id.dat_apellidos);
        d_correo=(EditText)findViewById(R.id.dat_correo);
        d_telefono=(EditText)findViewById(R.id.dat_telefono);
        d_pais=(EditText)findViewById(R.id.dat_pais);
        d_peticion=(EditText)findViewById(R.id.dat_peticion);

    }

    private void obtenerDatosIntent() {

        total=getIntent().getExtras().getString("total");
        taxRate=getIntent().getExtras().getFloat("iva");
        RuleTransactionId=getIntent().getExtras().getString("id");
        state=getIntent().getExtras().getString("state");
        city=getIntent().getExtras().getString("city");
        country=getIntent().getExtras().getString("country");
        zipCode=getIntent().getExtras().getString("zipCode");
        habitacion=getIntent().getExtras().getString("habitacion");
    }

    public void irFinal(){
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PonerEnHold();
            }
        });
    }

    public void termina(){
        if(statusx.equals("1")){
            Intent go = new Intent(InformacionPagoActivity.this,ConfimacionActivity.class);
            Bundle datos=new Bundle();
            datos.putString("habitacion",habitacion);
            datos.putString("total",total);
            datos.putFloat("iva",taxRate);
            datos.putString("nombre",d_nombre.getText().toString()+" "+d_apellidos.getText().toString());
            datos.putString("correo",d_correo.getText().toString());
            datos.putString("telefono",d_telefono.getText().toString());
            datos.putString("ciudad",d_pais.getText().toString());
            datos.putString("peticion",d_peticion.getText().toString());
            datos.putString("referencia",referenciax);
            go.putExtras(datos);
            startActivity(go);
        }else{
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
    }
}