package com.eklavya.ali.invoice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.Holder> {

    Context ctx;
    ArrayList<Modeltable> mReportList;
    public ReportAdapter(Context ctx , ArrayList<Modeltable> mReportList){
        this.ctx = ctx;
        this.mReportList = mReportList;

    }

    @Override
    public ReportAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reportdisplay, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(ReportAdapter.Holder holder, int position) {

        holder.productname.setText(mReportList.get(position).productname);
        holder.qty.setText(mReportList.get(position).qty);
        holder.rate.setText(mReportList.get(position).rate);
        holder.total.setText(mReportList.get(position).total);

    }

    @Override
    public int getItemCount() {
        return mReportList==null?0:mReportList.size();
    }

    public class Holder extends RecyclerView.ViewHolder  {
    TextView productname,qty,rate,total;
        public Holder(View itemView) {
            super(itemView);
            productname=(TextView) itemView.findViewById(R.id.productDsp);
            qty=(TextView) itemView.findViewById(R.id.qtyDsp);
            rate=(TextView) itemView.findViewById(R.id.rateDsp);
            total=(TextView) itemView.findViewById(R.id.totalDsp);


        }
    }
}
