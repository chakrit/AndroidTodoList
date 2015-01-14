package net.chakrit.androidtodolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private List<TodoItem> _tasks = new ArrayList<TodoItem>();

    private ListView getListView() {
        return (ListView) findViewById(R.id.list_tasks);
    }

    private ArrayAdapter<TodoItem> getAdapter() {
        return (ArrayAdapter<TodoItem>) getListView().getAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = getListView();
        listView.setAdapter(new ArrayAdapter<TodoItem>(this, android.R.layout.simple_list_item_checked));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.onItemTapped(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void onItemTapped(int position) {
        TodoItem task = _tasks.get(position);
        task.toggle();

        CheckedTextView textView = (CheckedTextView) getListView().getChildAt(position);
        textView.setChecked(task.isCompleted);
        getListView().setItemChecked(position, task.isCompleted);
    }

    private void onItemAdd(String description) {
        TodoItem newTask = new TodoItem();
        newTask.description = description;
        _tasks.add(newTask);
        getAdapter().add(newTask);
    }


    private void onNewItemTapped() {
        final EditText textBox = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("New item")
                .setView(textBox)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.onItemAdd(textBox.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                onNewItemTapped();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
