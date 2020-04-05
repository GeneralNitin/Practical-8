package com.nitin.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetch();
    }
    private void fetch() {
        ArrayList<String> contact = new ArrayList<>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String name = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        String projection[] = {name, number};
        String selection = null;
        String selectionArgs[] = null;
        String sortOrder = name;

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            String name1 = cursor.getString(cursor.getColumnIndex(name));
            String number1 = cursor.getString(cursor.getColumnIndex(number));
            contact.add(name1+"\n"+number1);
        }
        ListView listView = findViewById(R.id.mylist);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_layout, R.id.name, contact);
        listView.setAdapter(arrayAdapter);
    }
}