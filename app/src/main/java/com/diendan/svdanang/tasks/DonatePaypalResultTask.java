package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.PaypalDonationOutput;
import com.diendan.svdanang.api.models.ResultDonateOutput;
import com.diendan.svdanang.api.objects.PaypalDonationInput;
import com.diendan.svdanang.models.DataDonationDetailResult;

public class DonatePaypalResultTask extends BaseTask <ResultDonateOutput> {
    Long projectId;
    String paymentId,token,PayerID;
    public DonatePaypalResultTask(Context context, Long projectId,String paymentId, String token, String PayerID, @Nullable ApiListener<ResultDonateOutput> listener) {
        super(context, listener);
        this.projectId = projectId ;
        this.paymentId = paymentId ;
        this.token = token ;
        this.PayerID = PayerID ;
    }

    @Override
    protected ResultDonateOutput callApiMethod() throws Exception {
        return mApi.resultDonatePaypal(projectId,paymentId,token,PayerID);
    }
}
