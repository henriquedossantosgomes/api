package br.api.audora.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;


/**
* Conversão de Object (java) para byte[] para salvar no banco de dados;
* 
*
*/
public class BytesType implements UserType {

	private static final int[] SQL_TYPES = { Types.VARBINARY };  
    
	/**
     * Método que diz ao Hibernate qual o tipo de coluna SQL que será
     * usada na geração do schema DDL. Neste caso, BLOB.
     */
    public int[] sqlTypes() {  
        return SQL_TYPES;  
    }
    
    /**
     * Método que diz ao Hibernate qual tipo de classe está mapeada 
     * pelo UserType.
     */
    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
            return byte[].class;
    }
    
    public boolean equals(Object x, Object y) throws HibernateException {
            return x == y || (x != null && y != null && x.equals(y));
    }
    public int hashCode(Object x) throws HibernateException {
            return x.hashCode();
    }
    
    /**
     * Método que recupera o valor da propriedade a partir do ResultSet.
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
                    throws HibernateException, SQLException {
    	byte[] value = rs.getBytes(names[0]);
    	
    	if ( rs.wasNull() ) return null;  
        return Converter.convertByteArrayToObject(value);
    }
   
    /**
     * Método que escreve o valor da propriedade no PreparedStatement.
     * Este método é chamado antes de salvar.
     */
    public void nullSafeSet(PreparedStatement st, Object value, int index)
                    throws HibernateException, SQLException {
    	byte[] valor = Converter.convertObjectToByteArray(value);
            if (valor == null) {
                    st.setNull(index, Types.VARBINARY);
            } else {
                    st.setBytes(index, valor);
            }
    }
    
    public Object deepCopy(Object value) throws HibernateException {
            return value;
    }
    public boolean isMutable() {
            return false;
    }
    public Serializable disassemble(Object value) throws HibernateException {
            return (Byte) value;
    }
    public Object assemble(Serializable cached, Object owner)
                    throws HibernateException {
            return cached;
    }
    public Object replace(Object original, Object target, Object owner)
                    throws HibernateException {
            return original;
    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2, Object owner)
			throws HibernateException, SQLException {
		byte[] value = rs.getBytes(names[0]);
    	
    	if ( rs.wasNull() ) return null;  
        return Converter.convertByteArrayToObject(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor arg3)
			throws HibernateException, SQLException {
		byte[] valor = Converter.convertObjectToByteArray(value);
        if (valor == null) {
                st.setNull(index, Types.VARBINARY);
        } else {
                st.setBytes(index, valor);
        }
		
	}
	
}
