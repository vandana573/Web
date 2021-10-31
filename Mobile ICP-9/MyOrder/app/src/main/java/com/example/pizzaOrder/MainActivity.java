package com.example.pizzaOrder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }
    public void decrement(View view) {
        quantity=quantity-1;
        displayquantity(quantity);

    }
//entering quantity of item
    private void displayquantity(int quantity) {
        EditText quantity1 = (EditText) findViewById(R.id.quantityTxt);
        quantity1.setText(""+quantity);


    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayquantity(quantity);
    }


    public void displayMessage(String finalMessage) {
        //TextView Message = (TextView) findViewById(R.id.resultTxt);
        //Message.setText(""+finalMessage);
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("mes", finalMessage);
        startActivity(intent);

    }
//email when order button is pressed
    public String createFinalMessage(String names,Boolean FourInch, Boolean SixInch, Boolean Bellpepper, Boolean Greenonions,int price) {
        String Message = "\nDear "+names+"\n"+"Please check your order before proceeding \nPizza Size : \n"+"Selected FourInch : "+FourInch+
                "\n"+"Selected SixInch : "+SixInch+"\n"+"Toppings \n"+"Bell Pepper :"+Bellpepper+"\nGreen Onions :"+Greenonions+"\nQuantity :"+ quantity +"\nTotal Price $: "+price;
        return  Message;


    }
//calculating price
    public int calculatePrice(Boolean Bellpepper, Boolean Greenonions, boolean FourInch,boolean SixInch) {
        int price =10;
        if (Bellpepper)
        {
            price =price+2;
        }
        if (Greenonions)
        {
            price=price+3;
        }
        if (FourInch)
        {
            price=price+5;
        }
        if (SixInch)
        {
            price=price+9;
        }

        return price*quantity;
    }

    //write navigation to summary and in summary write this code to display
    public void orderButton(View view) {
        EditText name = (EditText) findViewById(R.id.nameTxt);

        String names = name.getText().toString();
        CheckBox Bellpepper = (CheckBox) findViewById(R.id.Bellpepper_checkbox);
        Boolean Bellpeppers = Bellpepper.isChecked();
        CheckBox Greenonion = (CheckBox) findViewById(R.id.Greenonions_checkbox);
        Boolean Greenonions = Greenonion.isChecked();
        CheckBox large = (CheckBox) findViewById(R.id.pizza1);
        Boolean largePizza = large.isChecked();
        CheckBox Regular = (CheckBox) findViewById(R.id.pizza2);
        Boolean regPizza = Regular.isChecked();
        int price = calculatePrice(Bellpeppers,Greenonions,regPizza,largePizza);
        String finalMessage =createFinalMessage (names,regPizza,largePizza,Bellpeppers,Greenonions,price);
        displayMessage(finalMessage);
    }

//    public void sendEmail(String output) {
//        // Write the relevant code for triggering email
//
//        // Hint to accomplish the task
//
//        /*Intent intent = new Intent(Intent.ACTION_VIEW);
//        if (intent.resolveActivity(getPackageManager()) !=null){
//            startActivity(intent);
//        }*/
//
//    }
    // linking with the email
    public void onlyOrder(View view)
    {
        EditText name = (EditText) findViewById(R.id.nameTxt);
        String names = name.getText().toString();
        CheckBox Bellpepper = (CheckBox) findViewById(R.id.Bellpepper_checkbox);
        Boolean Bellpeppers = Bellpepper.isChecked();
        CheckBox Greenonion = (CheckBox) findViewById(R.id.Greenonions_checkbox);
        Boolean Greenonions = Greenonion.isChecked();
        CheckBox large = (CheckBox) findViewById(R.id.pizza1);
        Boolean largePizza = large.isChecked();
        CheckBox Regular = (CheckBox) findViewById(R.id.Greenonions_checkbox);
        Boolean regPizza = Greenonion.isChecked();
        int price = calculatePrice(Bellpeppers,Greenonions,regPizza,largePizza);
        String finalMessage =createFinalMessage (names,regPizza,largePizza,Bellpeppers,Greenonions,price);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"priyankapia7@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Your Pizza Order");
        i.putExtra(Intent.EXTRA_TEXT   , finalMessage);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

}
