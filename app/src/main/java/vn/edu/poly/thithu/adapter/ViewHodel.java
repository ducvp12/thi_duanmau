package vn.edu.poly.thithu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.thithu.R;

public class ViewHodel extends RecyclerView.ViewHolder {
    public final TextView txtChinh,  txtPhu1;
    public final ImageView imageView, btndelete;


    public ViewHodel(View itemView) {
        super(itemView);

        txtChinh=itemView.findViewById(R.id.txtChinh);

        txtPhu1 = itemView.findViewById(R.id.txtphu2);
        imageView=itemView.findViewById(R.id.btnEdit);
        btndelete = itemView.findViewById(R.id.btnDelete);
    }
}
