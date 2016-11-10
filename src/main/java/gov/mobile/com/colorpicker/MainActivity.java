package gov.mobile.com.colorpicker;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar seekBar1, seekBar2, seekBar3;
    TextView hexDecimalText, choosenColor;
    int red = 0, green = 0, blue = 0;
    LinearLayout secondActivityLayout;
    SharedPreferences sharedPreferences;
    private String ColorPreferences = "colorpref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        seekBar1 = (SeekBar) findViewById(R.id.seek_1);
        seekBar2 = (SeekBar) findViewById(R.id.seek_2);
        seekBar3 = (SeekBar) findViewById(R.id.seek_3);
        secondActivityLayout = (LinearLayout) findViewById(R.id.layout_second_activity);
        hexDecimalText = (TextView) findViewById(R.id.hex_text);
       // choosenColor = (TextView) findViewById(R.id.color_text);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);


        sharedPreferences = getSharedPreferences(ColorPreferences, Context.MODE_PRIVATE);
        red = sharedPreferences.getInt("Red",0);
        green = sharedPreferences.getInt("Green",0);
        blue = sharedPreferences.getInt("Blue",0);

        seekBar1.setProgress(red);
        seekBar2.setProgress(green);
        seekBar3.setProgress(blue);

        setColorParams();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == seekBar1) {
            red = progress;
        }
        if (seekBar == seekBar2) {
            green = progress;
        }
        if (seekBar == seekBar3) {
            blue = progress;
        }
        setColorParams();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPreferences = getSharedPreferences(ColorPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Red",red);
        editor.putInt("Green",green);
        editor.putInt("Blue",blue);
        editor.commit();
    }

    private void setColorParams(){
        secondActivityLayout.setBackgroundColor(Color.rgb(red, green, blue));
        //choosenColor.setBackgroundColor(Color.rgb(red, green, blue));
        String hex = String.format("#%02x%02x%02x", red, green, blue);
        hexDecimalText.setText(hex);
    }
}
