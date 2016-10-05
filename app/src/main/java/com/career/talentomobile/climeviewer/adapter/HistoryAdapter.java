package com.career.talentomobile.climeviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.model.GeoInfo;

import java.util.List;

/**
 * Adapter for history entries shown in the slide listview of the fragment.
 */
public class HistoryAdapter extends ArrayAdapter<GeoInfo> {

    private final int resource;

    public HistoryAdapter(Context context, int resource, List<GeoInfo> items) {
        super(context, resource, items);
        this.resource = resource;
    }

    /* (non-Javadoc)
     * @see android.view.View#getView()
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View rowView = (convertView == null) ? LayoutInflater.from(parent.getContext())
            .inflate(resource, parent, false) : convertView;
        TextView nameTextView = (TextView) rowView.findViewById(R.id.row_place_name);

        GeoInfo geoInfo = getItem(position);
        if(geoInfo != null) {
            String name = geoInfo.getPlaceName();
            nameTextView.setText(name);
        }
        return rowView;
    }
}
