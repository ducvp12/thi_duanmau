package vn.edu.poly.thithu.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.thithu.R;
import vn.edu.poly.thithu.dao.DAO;
import vn.edu.poly.thithu.model.Model;

public class Adapter extends RecyclerView.Adapter<ViewHodel> {
private final Context context;
private final List<Model> xList;
private final DAO xDAO;

    public Adapter(Context context, List<Model> xList, DAO xDAO) {
        this.context = context;
        this.xList = xList;
        this.xDAO = xDAO;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_food,parent,false);


        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, final int position) {

        Model x=xList.get(position);
        holder.txtChinh.setText("A:  "+x.getA());

        holder.txtPhu1.setText("A:  "+x.getC());
        holder.imageView.setImageResource(R.mipmap.ic_launcher);

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong Bao");
                builder.setMessage("Ban Muon Xoa Ko?");
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        xDAO.delete(xList.get(position).getA());
                        xList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.setTitle(xList.get(position).getA());
                dialog.setContentView(R.layout.dialog_update);

                final EditText edtName,edtgia;

                edtName=dialog.findViewById(R.id.edtname);
                edtgia=dialog.findViewById(R.id.edtgia);

                edtName.setText(xList.get(position).getB());
                edtgia.setText(xList.get(position).getC());

                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Model user=new Model();
                        user.setA(xList.get(position).getA());
                        user.setB(edtName.getText().toString().trim());
                        user.setC(edtgia.getText().toString().trim());

                        xDAO.update(user);

                        // cap nhat thay doi len giao dien

                        xList.get(position).setC(edtName.getText().toString().trim());
                        xList.get(position).setB(edtName.getText().toString().trim());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return xList.size();
    }
}





