/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NUMERIC LITERAL</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getNot <em>Not</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getKeyword <em>Keyword</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getRealValue <em>Real Value</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getHexValue <em>Hex Value</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#isKeyWordValue <em>Key Word Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL()
 * @model
 * @generated
 */
public interface NUMERIC_LITERAL extends EObject
{
  /**
   * Returns the value of the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not</em>' attribute.
   * @see #setNot(String)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_Not()
   * @model
   * @generated
   */
  String getNot();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getNot <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Not</em>' attribute.
   * @see #getNot()
   * @generated
   */
  void setNot(String value);

  /**
   * Returns the value of the '<em><b>Keyword</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Keyword</em>' attribute.
   * @see #setKeyword(String)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_Keyword()
   * @model
   * @generated
   */
  String getKeyword();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getKeyword <em>Keyword</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Keyword</em>' attribute.
   * @see #getKeyword()
   * @generated
   */
  void setKeyword(String value);

  /**
   * Returns the value of the '<em><b>Int Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Int Value</em>' attribute.
   * @see #setIntValue(BigInteger)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_IntValue()
   * @model
   * @generated
   */
  BigInteger getIntValue();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getIntValue <em>Int Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Int Value</em>' attribute.
   * @see #getIntValue()
   * @generated
   */
  void setIntValue(BigInteger value);

  /**
   * Returns the value of the '<em><b>Real Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Real Value</em>' attribute.
   * @see #setRealValue(BigDecimal)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_RealValue()
   * @model
   * @generated
   */
  BigDecimal getRealValue();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getRealValue <em>Real Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Real Value</em>' attribute.
   * @see #getRealValue()
   * @generated
   */
  void setRealValue(BigDecimal value);

  /**
   * Returns the value of the '<em><b>Hex Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Hex Value</em>' attribute.
   * @see #setHexValue(BigInteger)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_HexValue()
   * @model
   * @generated
   */
  BigInteger getHexValue();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#getHexValue <em>Hex Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Hex Value</em>' attribute.
   * @see #getHexValue()
   * @generated
   */
  void setHexValue(BigInteger value);

  /**
   * Returns the value of the '<em><b>Key Word Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key Word Value</em>' attribute.
   * @see #setKeyWordValue(boolean)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getNUMERIC_LITERAL_KeyWordValue()
   * @model
   * @generated
   */
  boolean isKeyWordValue();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.NUMERIC_LITERAL#isKeyWordValue <em>Key Word Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key Word Value</em>' attribute.
   * @see #isKeyWordValue()
   * @generated
   */
  void setKeyWordValue(boolean value);

} // NUMERIC_LITERAL
