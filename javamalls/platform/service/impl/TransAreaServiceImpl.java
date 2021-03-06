package com.javamalls.platform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javamalls.base.basedao.IGenericDAO;
import com.javamalls.base.query.GenericPageList;
import com.javamalls.base.query.PageObject;
import com.javamalls.base.query.support.IPageList;
import com.javamalls.base.query.support.IQueryObject;
import com.javamalls.platform.domain.TransArea;
import com.javamalls.platform.service.ITransAreaService;

@Service
@Transactional
public class TransAreaServiceImpl implements ITransAreaService {
    @Resource(name = "transAreaDAO")
    private IGenericDAO<TransArea> transAreaDao;

    public boolean save(TransArea transArea) {
        try {
            this.transAreaDao.save(transArea);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public TransArea getObjById(Long id) {
        TransArea transArea = (TransArea) this.transAreaDao.get(id);
        if (transArea != null) {
            return transArea;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            this.transAreaDao.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean batchDelete(List<Serializable> transAreaIds) {
        for (Serializable id : transAreaIds) {
            delete((Long) id);
        }
        return true;
    }

    public IPageList list(IQueryObject properties) {
        if (properties == null) {
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(TransArea.class, query, params,
            this.transAreaDao);
        if (properties != null) {
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null) {
                pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj.getCurrentPage()
                    .intValue(), pageObj.getPageSize() == null ? 0 : pageObj.getPageSize()
                    .intValue());
            }
        } else {
            pList.doList(0, -1);
        }
        return pList;
    }

    public boolean update(TransArea transArea) {
        try {
            this.transAreaDao.update(transArea);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TransArea> query(String query, Map params, int begin, int max) {
        return this.transAreaDao.query(query, params, begin, max);
    }
}
