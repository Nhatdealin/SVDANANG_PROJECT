package com.diendan.svdanang.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.diendan.svdanang.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by dcmen on 08/31/16.
 */
public class StringUtils {

    public static String getString(Context context, String content) {
        if (content == null || (content != null && content.trim().length() == 0) || (content != null && content.trim().equalsIgnoreCase("null"))) {
            return context.getString(R.string.txt_no_text);
        }
        return content;
    }

    public static String formatPriceFromString(Context context, String price) {
        try {
            return formatPrice(Long.valueOf(price));
        } catch (NumberFormatException e) {
            return context.getString(R.string.txt_no_text);
        }
    }

    public static String formatPrice(long price) {
        DecimalFormat mDoubleFomatter = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
        mDoubleFomatter.applyPattern("###,##0");
        return mDoubleFomatter.format(price);
    }

    public static boolean isNull(Context context, String content) {
        if (content == null || (content != null && content.trim().length() == 0) || (content != null && content.trim().equalsIgnoreCase("null"))) {
            return true;
        }
        return false;
    }

    public static String getDateStringFromTimestampFull(long timestamp) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return mSimpleDateFormat.format(new Date(timestamp));
    }

    public static String getDateStringFromTimestamp(long timestamp) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return mSimpleDateFormat.format(new Date(timestamp - getOffsetInMillis()));
    }

    public static String getFullDate2StringFromTimestamp(long timestamp) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("hh:mm aaa, dd/MM/yyyy");
        return mSimpleDateFormat.format(new Date(timestamp - getOffsetInMillis()));
    }

    public static String getFullDate2StringFromTimestampNotTimeZone(long timestamp) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, hh:mm aaa");
        return mSimpleDateFormat.format(new Date(timestamp));
    }

    public static String getFullDate2StringFromTimestampTimeZone(long timestamp) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy, hh:mm aaa");
        return mSimpleDateFormat.format(new Date(timestamp - getOffsetInMillis()));
    }

    public static String formatDob(long secondTime) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (secondTime == 0) {
            return "Không có";
        }
        return mSimpleDateFormat.format(new Date((secondTime - 25200) * 1000));
    }

    public static String formatOnlyDateTime(long milisecondTime) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("hh:mm aaa");
        return mSimpleDateFormat.format(new Date(milisecondTime - getOffsetInMillis()));
    }

    public static int getOffsetInMillis() {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        return offsetInMillis;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }

    public static boolean isContainStringVN(String source, String cs) {
        Locale locale = new Locale("VN", "vi");
        String normalizeString = removeAccent(source).toLowerCase();
        return (source.contains(cs.toLowerCase(locale))
                || normalizeString.contains(cs.toLowerCase(locale)));
    }

    public static boolean isEmpty(String text) {
        return (TextUtils.isEmpty(text) || text.toLowerCase().equals("null"));
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        String PHONE_PATTERN = "^\\+[0-9]{10,13}$";

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static String toJSONString(Map<String, String> params) {
        JSONObject object = new JSONObject();
        for (String key : params.keySet()) {
            try {
                object.put("key", params.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object.toString();
    }

    public static String getParamsRequest(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        if (params.size() > 0) {
            for (String key : params.keySet()) {
                if (builder.length() > 0) {
                    builder.append("&");
                }
                builder.append(key).append("=");
                try {
                    builder.append(URLEncoder.encode(params.get(key), Constants.CHARSET));
                } catch (UnsupportedEncodingException e) {
                    builder.append(params.get(key));
                }
            }
        }
        return builder.toString();
    }

    public static String eliminateDecimal(double num) {
        return String.format("%.0f", num);
    }

    public static String formatDistanceInKm(float distanceInKm) {
        return String.format(Locale.US, "%.1f", distanceInKm);
    }

    public static String errorMessageForDelivery(Context context, String errorMessage) {
        String message = context.getString(R.string.err_unexpected_exception);
        if (errorMessage != null) {
            if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromIssuedToCompleted")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_status_trouble), context.getString(R.string.txt_status_progressed));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromCompletedToInprogress")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_status_progressed), context.getString(R.string.txt_status_progressing));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromCompletedToIssued")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_status_progressed), context.getString(R.string.txt_status_trouble));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromInprogressToInprogress")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_status_progressing), context.getString(R.string.txt_status_progressing));
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return message;
    }

    public static String errorMessageForTask(Context context, String errorMessage) {
        String message = context.getString(R.string.err_unexpected_exception);
        if (errorMessage != null) {
            if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromAuditingToInprogress")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_checking), context.getString(R.string.txt_excuting));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromNewToAuditing")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_will_excute), context.getString(R.string.txt_checking));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromInprogressToNew")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_excuting), context.getString(R.string.txt_will_excute));
            } else if (errorMessage.equalsIgnoreCase("CannotChangeStatusFromAuditingToNew")) {
                message = context.getString(R.string.txt_cant_change_status, context.getString(R.string.txt_checking), context.getString(R.string.txt_will_excute));
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return message;
    }
}
