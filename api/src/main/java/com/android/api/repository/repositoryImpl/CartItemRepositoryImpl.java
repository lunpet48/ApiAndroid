package com.android.api.repository.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.android.api.entity.CartItem;
import com.android.api.repository.repositoryCustom.CartItemRepoCustom;

public class CartItemRepositoryImpl implements CartItemRepoCustom {
    @Autowired
    DataSource dataSource;

    @Override
    public Optional<CartItem> findCartItem(Long cartId, Long productId, Long colorId, Long sizeId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM cart_items c WHERE c.cart_id = ?1 and c.product_id = ?2 and c.color_id = ?3 and c.size_id = ?4";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, cartId);
        ps.setLong(2, productId);
        ps.setLong(3, colorId);
        ps.setLong(4, sizeId);

        ResultSet rs = ps.executeQuery();
        CartItem cartItem = new CartItem();
        while(rs.next()){
            cartItem.setCart(null);
            cartItem.setCartItemId(0);
            cartItem.setColor(null);
            cartItem.setCount(0);
            cartItem.setCreateAt(null);
            cartItem.setIsDeleted((byte) 0);
            cartItem.setProduct(null);
            cartItem.setSize(null);
            cartItem.setUpdateAt(null);
        }
        return null;
    }

}