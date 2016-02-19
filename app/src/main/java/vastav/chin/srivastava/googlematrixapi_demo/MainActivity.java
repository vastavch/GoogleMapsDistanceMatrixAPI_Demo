package vastav.chin.srivastava.googlematrixapi_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*Replace YOUR_API_KEY in String url with your API KEY obtained by registering your application with google.

 */
public class MainActivity extends AppCompatActivity implements GeoTask.Geo {
    EditText edttxt_from,edttxt_to;
    Button btn_get;
    String str_from,str_to;
    TextView tv_result1,tv_result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_from=edttxt_from.getText().toString();
                str_to=edttxt_to.getText().toString();
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + str_from + "&destinations=" + str_to + "&mode=driving&language=fr-FR&avoid=tolls&key=YOUR_API_KEY";
                new GeoTask(MainActivity.this).execute(url);

            }
        });

    }

    @Override
    public void setDouble(String result) {
        String res[]=result.split(",");
        Double min=Double.parseDouble(res[0])/60;
        int dist=Integer.parseInt(res[1])/1000;
        tv_result1.setText("Duration= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");
        tv_result2.setText("Distance= " + dist + " kilometers");

    }
    public void initialize()
    {
        edttxt_from= (EditText) findViewById(R.id.editText_from);
        edttxt_to= (EditText) findViewById(R.id.editText_to);
        btn_get= (Button) findViewById(R.id.button_get);
        tv_result1= (TextView) findViewById(R.id.textView_result1);
        tv_result2=(TextView) findViewById(R.id.textView_result2);

    }
}
