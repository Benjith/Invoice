package com.eklavya.ali.invoice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableAdapter extends RecyclerView.Adapter<TableAdapter.Holder> {
    Context ctx;
    ArrayList<Modeltable> tablelist;
    String[] product = {"Trophy H110", "Combo 038", "Pen", "Pencil", "Book"};
    List<String> lstProduct = new ArrayList<String>();
    String[] rates = {"350", "660", "10", "5", "28"};
    Float totalAmount = 0f;
    Dashboard dashboard;

    public TableAdapter(Context ctx, ArrayList<Modeltable> mTablelist, Dashboard dash) {
        this.ctx = ctx;
        this.tablelist = mTablelist;
        lstProduct.addAll(Arrays.asList(product));
        dashboard = dash;
    }

    @Override
    public TableAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ArrayAdapter productname = new ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, product);
        Modeltable m = tablelist.get(position);
        holder.productlsit.setAdapter(productname);
        holder.productlsit.setSelection(lstProduct.indexOf(m.productname));
        holder.qty.setText(m.qty);
        holder.rate.setText(m.rate);
        holder.singletotal.setText(m.total);
        CalcTotal();

    }

    public void CalcTotal() {
        totalAmount = 0f;
        for (int i = 0; i < tablelist.size(); i++) {
            Modeltable m = tablelist.get(i);
            totalAmount = totalAmount + Float.parseFloat(m.total == "" ? "0" : m.total);

        }
        dashboard.totalamount.setText("Total : " + String.valueOf(totalAmount));
    }

    @Override
    public int getItemCount() {
        return tablelist == null ? 0 : tablelist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Spinner productlsit;
        EditText qty, rate;
        TextView singletotal;

        public Holder(View itemView) {
            super(itemView);
            try {
                productlsit = (Spinner) itemView.findViewById(R.id.pro_list);
                qty = (EditText) itemView.findViewById(R.id.singleQty);
                rate = (EditText) itemView.findViewById(R.id.singleRate);
                singletotal = (TextView) itemView.findViewById(R.id.singleTotal);

                productlsit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        rate.setText(rates[i]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                qty.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (!b) {
                            String Qty = ((EditText) view).getText().toString().trim();
                            View rateView = ((TableRow) view.getParent()).getChildAt(2);
                            EditText Rate = (EditText) rateView;
                            String rateVal = Rate.getText().toString();
                            View totalView = ((TableRow) view.getParent()).getChildAt(3);
                            float total = Float.parseFloat(Qty) * Float.parseFloat(rateVal);
                            ((TextView) totalView).setText(String.valueOf(total));
                            Modeltable m = tablelist.get(getAdapterPosition());
                            View productView = ((TableRow) view.getParent()).getChildAt(0);
                            Spinner productname = (Spinner) productView;
                            m.productname = productname.getSelectedItem().toString();
                            m.qty = Qty;
                            m.rate = rateVal;
                            m.total = String.valueOf(total);
                        }
                        CalcTotal();
                        dashboard.arrayList=tablelist;
                    }
                });
//                qty.setOnKeyListener(new View.OnKeyListener() {
//                    @Override
//                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
//                            String Qty = ((EditText) view).getText().toString().trim();
//                            View rateView = ((TableRow) view.getParent()).getChildAt(2);
//                            EditText Rate = (EditText) rateView;
//                            String rateVal = Rate.getText().toString();
//                            int j=0;
//                        }
//                        return false;
//                    }
//                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        public void updateVals() {
//            for (int i = 0; i < tablelist)
        }
    }
}
