package dsardy.in.testo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProductViewActivity extends AppCompatActivity {


    TextView textViewpname, textViewpman, textViewpprise, textViewptype;
    Button buttonaddtocart;
    DatabaseReference prods,mycart;
    FirebaseDatabase database;
    Product p;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ed = sharedPreferences.edit();

        database = FirebaseDatabase.getInstance();
        prods = database.getReference("products");
        mycart = database.getReference("users").child(sharedPreferences.getString("rmn","")).child("orders");





        textViewpman = (TextView)findViewById(R.id.textViewdman);
        textViewpprise = (TextView)findViewById(R.id.textViewdprisse);
        textViewpname = (TextView)findViewById(R.id.textViewdname);
        textViewptype = (TextView)findViewById(R.id.textViewdtype);

        prods.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals(getIntent().getStringExtra("obj"))){
                    p = dataSnapshot.getValue(Product.class);

                    textViewpman.setText(p.getpManuf());
                    textViewptype.setText(p.getpType());
                    textViewpname.setText(p.getpName());
                    textViewpprise.setText(p.getPrice().toString());


                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        buttonaddtocart = (Button)findViewById(R.id.buttonaddtocart);

        buttonaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(p.getStock()>0){
                    p.setStock(p.getStock()-1);
                    prods.child(getIntent().getStringExtra("obj")).setValue(p);

                    Calendar c = Calendar.getInstance();
                    int Date = c.get(Calendar.YEAR);
                    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                    String month_name = month_date.format(c.getTime());

                    p.setDate(Date);
                    p.setMonth(month_name);

                    mycart.push().setValue(p);
                    Toast.makeText(getApplicationContext(),"Product added To your cart",Toast.LENGTH_LONG).show();


                }else {
                    Toast.makeText(getApplicationContext(),"Product Out of Stock",Toast.LENGTH_LONG).show();
                }






            }
        });

    }
}
