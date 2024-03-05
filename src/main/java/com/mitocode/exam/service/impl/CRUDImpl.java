package com.mitocode.exam.service.impl;

import com.mitocode.exam.exception.ModelNotFoundException;
import com.mitocode.exam.repository.IGenericRepository;
import com.mitocode.exam.service.ICRUD;

import java.lang.reflect.Method;
import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepository();

    @Override
    public T save(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        //Java reflection
        Class<?> clazz = t.getClass();
        String className = clazz.getSimpleName();
        String methodName = "setId" + className;

        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);

        getRepository().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND " + id));
        return getRepository().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepository().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepository().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND " + id));
        getRepository().deleteById(id);
    }
}
