package br.com.bossini.tipcalculatorfateccarapicuibasabado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private  String zeroFormatado;
    private  double billAmount = 0L;
    private  double tipPercentage = .15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        zeroFormatado = currencyFormat.format(0);

        amountTextView.setText(zeroFormatado);
        tipTextView.setText(zeroFormatado);
        totalTextView.setText(zeroFormatado);
        SeekBar seekBar = findViewById(R.id.percentSeekBar);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tipPercentage = progress/100.0 ; //or (double)100;
                        String valorFormatado = percentFormat.format(tipPercentage);
                        percentTextView.setText(valorFormatado);
                        double tip = tipPercentage * billAmount;
                        double total = tip +billAmount;
                        tipTextView.setText(currencyFormat.format(total));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        EditText amountEditText = findViewById(R.id.amountEditText);

        ObservadorDoEditText observadorDoEditText = new ObservadorDoEditText();
        amountEditText.addTextChangedListener(observadorDoEditText);

    }

    private class ObservadorDoEditText implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{

                billAmount = Double.parseDouble(charSequence.toString())/100;
                amountTextView.setText(currencyFormat.format(billAmount));
                double tip = tipPercentage * billAmount;
                double total = tip +billAmount;
                tipTextView.setText(currencyFormat.format(total));
            }
            catch (NumberFormatException x){
                amountTextView.setText(zeroFormatado);
                tipTextView.setText(zeroFormatado);
                totalTextView.setText(zeroFormatado);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
