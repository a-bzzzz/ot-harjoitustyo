/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

/**
 *
 * @author aebjork
 */
public class Info<K, V> {
    
    private K user;
    private V text;

    public Info(K user, V text) {
        this.text = text;
    }

    public void setUser(K user) {
        this.user = user;
    }

    public void setText(V text) {
        this.text = text;
    }

    public K getUser() {
        return this.user;
    }

    public V getText() {
        return this.text;
    }

}
