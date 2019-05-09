package com.diendan.svdanang.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.diendan.svdanang.api.ApiListener;
import com.diendan.svdanang.api.models.PaypalDonationOutput;
import com.diendan.svdanang.api.models.SignupOutput;
import com.diendan.svdanang.api.objects.PaypalDonationInput;
import com.diendan.svdanang.api.objects.SignupInput;

public class DonatePaypalTask extends BaseTask <PaypalDonationOutput> {
    PaypalDonationInput paypalDonationInput;
    public DonatePaypalTask(Context context, PaypalDonationInput paypalDonationInput, @Nullable ApiListener<PaypalDonationOutput> listener) {
        super(context, listener);
        this.paypalDonationInput = paypalDonationInput ;
    }

    @Override
    protected PaypalDonationOutput callApiMethod() throws Exception {
        return mApi.donatePaypal(paypalDonationInput);
    }
}
