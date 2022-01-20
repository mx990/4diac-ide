/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ST Date Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STDateLiteral#getDateLiteral <em>Date Literal</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTDateLiteral()
 * @model
 * @generated
 */
public interface STDateLiteral extends STExpression
{
  /**
   * Returns the value of the '<em><b>Date Literal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Date Literal</em>' containment reference.
   * @see #setDateLiteral(DATE_LITERAL)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTDateLiteral_DateLiteral()
   * @model containment="true"
   * @generated
   */
  DATE_LITERAL getDateLiteral();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STDateLiteral#getDateLiteral <em>Date Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Date Literal</em>' containment reference.
   * @see #getDateLiteral()
   * @generated
   */
  void setDateLiteral(DATE_LITERAL value);

} // STDateLiteral
