/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.model.intf;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author petroff
 */
public interface TreeIntf<T> extends Serializable {

    public List<T> getBranches();

}
