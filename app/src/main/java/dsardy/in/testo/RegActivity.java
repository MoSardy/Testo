package dsardy.in.testo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {

    EditText name, pass , mb ;
    Button buttonReg;
    TextView gotoLogin;
    DatabaseReference users;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        database = FirebaseDatabase.getInstance();
        users = database.getReference("users");

        name = (EditText)findViewById(R.id.editTextName);
        pass = (EditText)findViewById(R.id.editTextPass);
        mb = (EditText)findViewById(R.id.editTextMobile);

        buttonReg = (Button)findViewById(R.id.buttonreg);
        gotoLogin = (TextView)findViewById(R.id.textViewgotologin);


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mb.getText().toString().isEmpty()||pass.getText().toString().isEmpty()||name.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Your Details Man!",Toast.LENGTH_LONG).show();
                }else {
                    final User user = new User(mb.getText().toString(),name.getText().toString(),pass.getText().toString());

                    //check if already registered

                    users.child(user.getMb()).setValue(user);

                    Toast.makeText(getApplicationContext()," U are Successfully Registered ",Toast.LENGTH_LONG ).show();

                    startActivity(new Intent(getApplication(),LoginActivity.class));
                    finish();
                }

            }
        });


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }
        });




    }
}
