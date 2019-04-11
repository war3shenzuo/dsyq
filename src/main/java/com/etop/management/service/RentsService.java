package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Rents;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/8
 */
public interface RentsService {

    EtopPage<Rents> search(Rents rents, String type);

}
