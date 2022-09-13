package com.example.study_admin.ifs;

import com.example.study_admin.model.network.Header;

public interface CrudInterface {
    Header create(); // todo request object 추가
    Header read(Long id);
    Header update();
    Header delete(Long id);
}
