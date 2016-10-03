package com.career.talentomobile.climeviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.model.GeoInfo;

import java.util.List;

/**
 * Created by malkomich on 03/10/2016.
 */

public class HistoryAdapter extends ArrayAdapter {

    private final String TAG = HistoryAdapter.class.getName();

    private final Context context;
    private final int resource;

    public HistoryAdapter(Context context, int resource, List<GeoInfo> items) {
        super(context, resource, items);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = (convertView == null) ? inflater.inflate(resource, parent, false) : convertView;
        TextView nameTextView = (TextView) rowView.findViewById(R.id.row_place_name);

        GeoInfo geoInfo = (GeoInfo) getItem(position);
        String name = geoInfo.getPlaceName();
        nameTextView.setText(name);

        return rowView;
    }
}
