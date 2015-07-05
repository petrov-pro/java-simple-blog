/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.intf;

import blog.system.exception.PersistException;

/**
 *
 * @author petroff
 */
public interface DaoGeneric<T> {

    public T findByPk(int id) throws PersistException ;

}
