package com.sipi1020.bitcoinpricetracker.di;

/**
 * Created by Viki on 2018-04-20.
 */

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME) // not needed
public @interface Network {
}