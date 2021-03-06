package com.timsoft.meurebanho.infra;

import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DecimalTextWatcher extends AbstractDecimalTextWatcher {

    public DecimalTextWatcher(EditText editText) {
        super(editText);
    }

    protected NumberFormat getNumberFormat() {
        return new DecimalFormat("#,###.00");
        //return NumberFormat.getNumberInstance();
    }
}
/*
public class MoneyTextWatcher implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;

    public MoneyTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference<EditText>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        String s = editable.toString();
        editText.removeTextChangedListener(this);
        String cleanString = s.toString().replaceAll("[^\\d]", "");
        if (!TextUtils.isEmpty(cleanString)) {
            BigDecimal parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
            String formatted = NumberFormat.getCurrencyInstance().format(parsed);
            editText.setText(formatted);
            editText.setSelection(formatted.length());
        }
        editText.addTextChangedListener(this);
    }
}
*/