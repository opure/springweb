package com.oneler.persistence;

import com.oneler.domain.MonetaryAmount;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;

/**
 * This is a simple Hibernate custom mapping type for MonetaryAmount value types.
 * <p>
 * Note that this mapping type is for legacy databases that only have a
 * single numeric column to hold monetary amounts. Every <tt>MonetaryAmount</tt>
 * will be converted to USD (using some conversion magic of the class itself)
 * and saved to the database.
 *
 * @author Christian Bauer
 */
public class MonetaryAmountSimpleUserType
        implements UserType {

    public int[] sqlTypes() {
        return new int[]{StandardBasicTypes.BIG_DECIMAL.sqlType()};
    }

    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    public boolean isMutable() {
        return false;
    }

    public Object deepCopy(Object value) {
        return value;
    }

    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

    public boolean equals(Object x, Object y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return x.equals(y);
    }

    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {

    }

    public Object nullSafeGet(ResultSet resultSet,
                              String[] names,
                              Object owner)
            throws HibernateException, SQLException {

        BigDecimal valueInUSD = resultSet.getBigDecimal(names[0]);
        if (resultSet.wasNull()) return null;
        Currency userCurrency = Currency.getInstance("USD");
        return new MonetaryAmount(valueInUSD, userCurrency);
    }

    public void nullSafeSet(PreparedStatement statement,
                            Object value,
                            int index)
            throws HibernateException, SQLException {

        if (value == null) {
            statement.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
        } else {
            MonetaryAmount anyCurrency = (MonetaryAmount) value;
            MonetaryAmount amountInUSD =
                    MonetaryAmount.convert(anyCurrency,
                            Currency.getInstance("USD"));
            statement.setBigDecimal(index, amountInUSD.getValue());
        }
    }
}
