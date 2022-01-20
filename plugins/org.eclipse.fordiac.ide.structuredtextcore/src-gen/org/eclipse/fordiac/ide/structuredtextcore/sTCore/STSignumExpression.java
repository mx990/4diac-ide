/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ST Signum Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STSignumExpression#getSignum <em>Signum</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STSignumExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTSignumExpression()
 * @model
 * @generated
 */
public interface STSignumExpression extends STExpression
{
  /**
   * Returns the value of the '<em><b>Signum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Signum</em>' attribute.
   * @see #setSignum(String)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTSignumExpression_Signum()
   * @model
   * @generated
   */
  String getSignum();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STSignumExpression#getSignum <em>Signum</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Signum</em>' attribute.
   * @see #getSignum()
   * @generated
   */
  void setSignum(String value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(STExpression)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTSignumExpression_Expression()
   * @model containment="true"
   * @generated
   */
  STExpression getExpression();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STSignumExpression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(STExpression value);

} // STSignumExpression
