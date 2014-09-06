package com.ziverge.fundamentalsweekone;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity implements AdapterView.OnItemSelectedListener {

    //stringList array holding data for information added by teh user
    public ArrayList stringList = new ArrayList<String>();

    //fields and buttons
    private Spinner cClassSpinner;
    private EditText cNameText;
    public Button cClearBtn;
    public Button cCreateBtn;
    private TextView entryTextView;
    private TextView lengthTextView;
    public ListView listView;
    public String cNameString;

    //array adapter for cAdapter attached to listView
    public ArrayAdapter<String>cAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //obtain all fields
        cNameText = (EditText) findViewById(R.id.cNameText);
        cClearBtn = (Button) findViewById(R.id.cClearBtn);
        cCreateBtn = (Button) findViewById(R.id.cCreateBtn);
        entryTextView = (TextView) findViewById(R.id.totalEntryTextView);
        lengthTextView = (TextView) findViewById(R.id.averageLengthTextView);

        //ListView adapter
        listView = (ListView) findViewById(R.id.listView);
        cAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(cAdapter);
        listView.setOnItemSelectedListener(this);

        //ArrayAdapter building from the strings.xml file for cClassSpinner
        cClassSpinner = (Spinner) findViewById(R.id.cClassSpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.character, android.R.layout.simple_spinner_dropdown_item);
        cClassSpinner.setAdapter(adapter);
        cClassSpinner.setOnItemSelectedListener(this);

    }

    //create button function
    public void cCreateBtnOnClick(View v) {

        //add the text added by the user to a string
        cNameString = cNameText.getText().toString();

        //if statement checking for duplicates comparing it to the information in the listView
        if(stringList.contains(cNameText.getText().toString() +" " + "The " + " " + String.valueOf(cClassSpinner.getSelectedItem()))) {

            //display toast message if there are duplicates
            Toast.makeText(this, "Duplicates are not permitted", Toast.LENGTH_SHORT).show();

            //calls the clear all function
            clearAll();

        } else if(!cNameString.equals("")) {


            stringList.add(cNameString +" " + "The " + " " + String.valueOf(cClassSpinner.getSelectedItem()));

            Toast.makeText(this, "Character Saved", Toast.LENGTH_SHORT).show();

            cAdapter.notifyDataSetChanged();

            entryTextView.setText("Total Entries" + String.valueOf(stringList.size()));

            //calls the method for the average length
            lengthCalculations();

        } else {

            //toast message nothing can be left empty
            Toast.makeText(this, "Please verify nothing is empty", Toast.LENGTH_LONG).show();
        }
    }

    public void lengthCalculations() {

        //float value for calculations
        float size = 0;

        //for loop running through string array for calculation
        for (int i = 0; i<stringList.size(); i++) {
            String currentItem = String.valueOf(stringList.get(i));
            size += currentItem.length();

        }
        //takes variable from for loop and applies the calculation
        size = size / stringList.size();

        //displays the calculation
        lengthTextView.setText("Length Average: " + size);

    }


    public void cClearOnClick(View v) {

        //add clear button properties
        clearAll();
    }

    //function to clear all fields
    public void clearAll() {

        cNameText.setText("");
        cClassSpinner.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
