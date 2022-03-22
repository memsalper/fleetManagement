package com.works.fleet_management.services.abstracts.base;

import com.works.fleet_management.core.utilities.results.Result;
import com.works.fleet_management.model.abstarcts.IDto;

public interface IBaseService <T extends IDto>  {

    Result save(T t);

    //Bu kısıma verilen taslak için ihtiyacımız olmayacak
    /*Result update(Long id, T t);

    Result softDelete(Long id);

    Result delete(Long id);*/

}
