package dsardy.in.testo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText editTextMbt, editTextPasst;
    Button buttonlogin;
    TextView textViewgotoreg;
    DatabaseReference users;
    FirebaseDatabase database;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ed = sharedPreferences.edit();

        database = FirebaseDatabase.getInstance();
        users = database.getReference("users");

        editTextMbt = (EditText)findViewById(R.id.editTextMobilel);
        editTextPasst = (EditText)findViewById(R.id.editTextPassc);
        buttonlogin = (Button)findViewById(R.id.button);
        textViewgotoreg = (TextView)findViewById(R.id.textViewgotoreg);

       buttonlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               if(editTextMbt.getText().toString().isEmpty()||editTextPasst.getText().toString().isEmpty()){

               }else {


                   users.child(editTextMbt.getText().toString()).child("password").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           String s = dataSnapshot.getValue(String.class);
                           if(s.equals(editTextPasst.getText().toString())){

                               ed.putString("rmn",editTextMbt.getText().toString());
                               if(ed.commit()){
                                   startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                   finish();
                               }



                           }else {
                               Toast.makeText(getApplicationContext()," Password inCorrect !",Toast.LENGTH_LONG).show();
                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });





               }




           }
       });

        textViewgotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),RegActivity.class));
                finish();

            }
        });
    }
}
