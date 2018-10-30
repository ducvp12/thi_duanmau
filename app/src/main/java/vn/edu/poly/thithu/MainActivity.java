package vn.edu.poly.thithu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.thithu.adapter.Adapter;
import vn.edu.poly.thithu.dao.DAO;
import vn.edu.poly.thithu.database.DatabaseHelper;
import vn.edu.poly.thithu.model.Model;

public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager;
    private Adapter adapter;
    private List<Model> list;
    private DAO dao;
    private Button btnThem;
    private EditText edt1;
    private EditText edt2;
    private EditText edt3;
    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        anhxa();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        dao = new DAO(databaseHelper);

        for (int i = 0; i < 4; i++) {
            Model a = new Model("ID: " + i, "name: " + 1, (i * 5) + "");
            dao.insert(a);
        }
        Add();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model=new Model();
                model.setA(edt1.getText().toString().trim());
                model.setB(edt2.getText().toString().trim());
                model.setC(edt3.getText().toString().trim());

                dao.insert(model);

                // cap nhat len giao dien
                // add vao vi tri dau tien

                list.add(0,model);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void anhxa() {
        recyclerview = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list = new ArrayList<>();
        list.clear();
    }


    public void Add(){

        list=dao.getAll();
        adapter=new Adapter(this,list,dao);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(adapter);
    }


    private void initView() {
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        btnThem=findViewById(R.id.btn_them);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }
}
