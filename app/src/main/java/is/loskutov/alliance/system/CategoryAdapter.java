package is.loskutov.alliance.system;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import is.loskutov.alliance.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener {

    private String[] data;

    class ViewHolder extends RecyclerView.ViewHolder {

        Button button;

        ViewHolder(View itemView) {
            super(itemView);

            button = (Button) itemView.findViewById(R.id.category_button);
        }
    }

    public CategoryAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buttons_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        holder.button.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public void onClick(View v) {
    }
}
