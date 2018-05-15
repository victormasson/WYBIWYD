package masson.diiage.org.wybiwyd.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import masson.diiage.org.wybiwyd.Entities.Beer;
import masson.diiage.org.wybiwyd.R;

public class BeerAdapter extends BaseAdapter {
    private ArrayList<Beer> listBeer;
    private Activity context;
    private LayoutInflater layoutInflater;

    public BeerAdapter(ArrayList<Beer> listComment, Activity context) {
        this.listBeer = listComment;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return listBeer.size(); }

    @Override
    public Beer getItem(int i) { return listBeer.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // La vue qui est retournée
        View view = convertView;
        // Permet de mémoriser les calculs de findByIds
        BeerViewHolder beerViewHolder;

        // La vue est recyclée si le convertView est null
        if (convertView != null) {
            // On récupère la vue
            beerViewHolder = (BeerViewHolder)view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.item_beer, null);
            beerViewHolder = new BeerViewHolder((TextView) view.findViewById(R.id.beerName),
                    (TextView) view.findViewById(R.id.beerDescription),
                    (TextView) view.findViewById(R.id.beerAlccol),
                    (TextView) view.findViewById(R.id.beerPrice));
            view.setTag(beerViewHolder);
        }

        Beer beer = getItem(i);
        beerViewHolder.labelName.setText(beer.getName());
        beerViewHolder.labelDescription.setText(beer.getDescription());
        beerViewHolder.labelAlcool.setText(String.valueOf(beer.getAlcool()));
        beerViewHolder.labelPrice.setText(String.valueOf(beer.getPrice()));

        return view;
    }

    private class BeerViewHolder {
        public TextView labelName;
        public TextView labelDescription;
        public TextView labelAlcool;
        public TextView labelPrice;

        public BeerViewHolder() {
        }

        public BeerViewHolder(TextView lName, TextView lDescription, TextView lAlcool, TextView lPrice) {
            this.labelName = lName;
            this.labelDescription = lDescription;
            this.labelAlcool = lAlcool;
            this.labelPrice = lPrice;
        }
    }
}
