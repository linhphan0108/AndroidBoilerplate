package com.linhphan.androidboilerplate.api.Parser;

import com.linhphan.androidboilerplate.api.BaseWorker;

/**
 * Created by linhphan on 11/12/15.
 */
public interface IParser {
    Object parse(Object data, BaseWorker.ResponseCodeHolder responseCode);
}
