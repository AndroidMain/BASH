package ru.proglive.android1.lesson4.alyoshin.bash_reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MyListAdapter extends ArrayAdapter<String> {


    private final Context context;
    private final String[] quotes;

    public MyListAdapter(Context context, String[] objects) {
        super(context, R.layout.row_view, objects);
        this.context = context;
        this.quotes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_view, parent, false);
        if (rowView == null) {
            return super.getView(position, convertView, parent);
        }
        TextView textView = (TextView) rowView.findViewById(R.id.quoteText);
        textView.setText(quotes[position]);
        return rowView;


    }
}
