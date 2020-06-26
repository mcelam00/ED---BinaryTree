package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o: âˆ…
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no estÃ¡ bien configurado en Eclipse para
 * usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o
 * 		como "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con 
 * 		raÃ­z "A" y un Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro
 * formato; por ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  âˆ…
 * |  |  âˆ…
 * |  S
 * |  |  âˆ…
 * |  |  âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar informaciÃ³n extra. Si es el caso, tanto toString() como
 * render() mostrarÃ¡n los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sÃ³lo con exactamente
 * T sino tambiÃ©n con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends //esto es como un nodo, porque un nodo es un arbol, tiene arbol izdo, derecho y content y un padre que hay que definir
		AbstractBinaryTreeADT<T> {

   BinarySearchTreeImpl<T> father;  //referencia a su nodo padre)

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeImpl() {
		//HACER QUE THIS SEA EL NODO VACÃ�O
		this.setLeftBST(null);
		this.setRightBST(null);
		this.setContent(null);
	
	}
	
	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		//HACER QUE THIS SEA EL NODO VACÃ�O, asignando como padre el parÃ¡metro recibido
		this.setLeftBST(null);
		this.setRightBST(null);
		this.setContent(null);

		this.father = father;
			
	}


	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);
	}
	
	/**
	 * Inserta los elementos de una colecciÃ³n en el Ã¡rbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los inserta)
	 */
	public int insert(Collection<T> elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		   int contador = 0;
				/*Comprobacion de la entrada*/
			
			 for (T elementoSubI : elements) {
				 if(elementoSubI == null) {
					 return contador; //Si algún elemento es null no se inserta ninguno
				 }
		 
		      }

			/*Inserción*/
			
			 for (T elementoSubI : elements) { /*El for each ya hace uso del iterador*/
		          
				 if(insert(elementoSubI)==true) { //siempre que el insert retorne true, contamos como insertado
					 contador++;
				 }
		      }

			 return contador;
		
	
	}

	/**
	 * Inserta los elementos de un array en el Ã¡rbol.
	 *  si alguno es 'null', NO INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los inserta)
	 */
	public int insert(T ... elements) {
		//	 si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
	   int contador = 0;
			/*Comprobacion de la entrada*/
		
		 for (T elementoSubI : elements) {
			 if(elementoSubI == null) {
				 return contador; //Si algún elemento es null no se inserta ninguno
			 }
	 
	      }

		/*Inserción*/
		
		 for (T elementoSubI : elements) {
	          
			 if(insert(elementoSubI) == true) { //siempre que el insert retorne true, contamos como insertado
				 contador++;
			 }
	      }

		 return contador;
	}
	
	/**
	 * Inserta (como hoja) un nuevo elemento en el Ã¡rbol de bÃºsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null si es la raÃ­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepciÃ³n: IllegalArgumentException 
	 *  Si el elemento ya existe en el Ã¡rbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false en caso contrario
	 * @throws IllegalArgumentException si element es null           
	 */
	public boolean insert(T element) {
	boolean insertado = false;
		
		if(element == null) { //Ambiguedad por sobrecarga de metodo
			throw new IllegalArgumentException();			
		}
	 	
		if(isEmpty()) { //el arbol es un nodo vacío o se ha encontrado
			this.setContent(element);
			this.setLeftBST(this.emptyBST(this));
			this.setRightBST(this.emptyBST(this));
			
			insertado = true;
		}
		else {
			
			if(element.compareTo(this.getContent()) < 0) { //mi elemento es menor que la raiz
				//llamamos por la izquierda
				insertado = this.getLeftBST().insert(element);
			}
			else if(element.compareTo(this.getContent()) >0){ //mi elemento es mayor que el de la raiz
				//llamo por la derecha
				insertado = this.getRightBST().insert(element); 
			}
			else {
				insertado = false;
			}
			
			
		}
		
		return insertado;	
	
	}


	/**
	 * Busca el elemento en el Ã¡rbol.
	 * 
	 * No se permiten elementos null. 
	 * 
	 * @param element   valor a buscar.
	 * @return true si el elemento estÃ¡ en el Ã¡rbol, false en caso contrario          
	 */
	public boolean contains(T element) {
		
		
		if(element == null) {
			return false;			
		}
		
		if(isEmpty()) {  //caso vacío o no se encuentra
			return false;
			
		}		
		else if(this.getContent().compareTo(element) == 0) {//si lo encuenta
			
			return true;
			
		}
		else {
			//Puedo descartar la mitad de las posibilidades al ser binario de busqueda
			if(this.getContent().compareTo(element) < 0) {
				return this.getRightBST().contains(element);
				
			}
			else {
				
				return this.getLeftBST().contains(element);
				
			}
			
						
		}	
		
	}
	
	/**
	 * Elimina los valores en un array del Ã¡rbol.
	 * O todos o ninguno; si alguno es 'null'o no lo contiene el Ã¡rbol, no se eliminarÃ¡ ningÃºn elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no estÃ¡ en el Ã¡rbol           
	 */
	public void remove(T ... elements) {

				/*Comprobacion de la entrada*/
			
			 for (T elementoSubI : elements) {
				 if(elementoSubI == null || contains(elementoSubI) == false) {
					 return; //si algun elemento es null o no es contenido en el arbol no se elimina ninguno
				 }
		 
		      }

			/*Eliminacion*/
			
			 for (T elementoSubI : elements) {
		          
				 remove(elementoSubI);

		      }

	
	}
	
	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomarÃ¡ el criterio de sustituir el elemento por
	 *  el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el Ã¡rbol           
	 */
	public void remove(T element) {

		if(isEmpty()) { //sea porque el arbol es un nodo vacío o porque hemos ido bajando por el camino de busqueda y hemos dado con el final sin enecontrarlo
			throw new NoSuchElementException();
		}
		else if(this.getContent().compareTo(element) == 0){ //lo encontramos
			
			//pueden darse 3 casos: sea hoja, de que tenga un hijo (siendo drcho o izdo) o que tenga 2 (menor de los mayores sutitucion + eliminacion con la derecha xq es el menor de los mayores)
			
			if(this.getRightBST().isEmpty() && this.getLeftBST().isEmpty()) {  //es hoja
				this.setContent(null);
				this.setRightBST(null);
				this.setLeftBST(null);
				
			}
			else if(this.getRightBST().isEmpty() || this.getLeftBST().isEmpty()) { //1 hijo, que puede ser el derecho o el izquierdo
				if(this.getLeftBST().isEmpty()) { //si es el hijo de la izda el vacio
					BinarySearchTreeImpl<T> derecha = this.getRightBST();
					
					this.setLeftBST(derecha.getLeftBST());
					this.setContent(derecha.content);
					this.setRightBST(derecha.getRightBST());

				}
				else { //es el de la derecha
					BinarySearchTreeImpl<T> izquierda = this.getLeftBST();

					this.setRightBST(izquierda.getRightBST());
					this.setContent(izquierda.content);
					this.setLeftBST(izquierda.getLeftBST());				
					
				}
				
				
				
			}
			else { //tiene dos hijos
				
				//busco el menor de los mayores y lo sustituyo. LLamo a eliminar con el
				
					//me voy a la drcha y luego todo a la izda iterativamente
				BinarySearchTreeImpl<T> aux = this; //para no cambiar el this
				
			
					aux = aux.getRightBST();

					while(!aux.getLeftBST().isEmpty()) { //mientras tenga izda me muevo, si la izquierda ya es nodo vacio no
						aux = aux.getLeftBST();
					}
				
				//ya llegue al menor de los mayores sustituyo this por aux
					this.setContent(aux.getContent());
				
				
				//llamo con this.right (para que no me elimine el this que acabo de sustituir) a este metodo recursivamente
				
				this.getRightBST().remove(aux.getContent());
				
				
			}
		
		}
		else {
			//seguimos buscando por el arbol
			
			
			if(this.getContent().compareTo(element) > 0) {
				
				this.getLeftBST().remove(element);				
				
			}
			else {
				this.getRightBST().remove(element);
			}
		
		}
				
	}
	
	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la altura del nodo.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  20
     * |  |  15
     * |  |  |  âˆ…
     * |  |  |  âˆ… 
     * |  |  30
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 *   {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], âˆ…, âˆ…}, âˆ…},
	 *               {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public void tagHeight() {
	
		if(!isEmpty()) {
			
			
			if(this.father == null) {
				
				this.setTag("height", 1);
				this.getLeftBST().tagHeight();
				this.getRightBST().tagHeight();

				
			}
			else {
				this.setTag("height", 1+(int)this.father.getTag("height"));
				this.getLeftBST().tagHeight();
				this.getRightBST().tagHeight();

			}

		}

	}
	
	
	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al nÃºmero de descendientes que tiene en este Ã¡rbol.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  20
     * |  |  15
     * |  |  |  âˆ…
     * |  |  |  âˆ… 
     * |  |  30
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 *  {10 [(decendents, 5)], 
	 *       {5 [(decendents, 1)], {2 [(decendents, 0)], âˆ…, âˆ…}, âˆ…}, 
	 *       {20 [(decendents, 2)], {15 [(decendents, 0)], âˆ…, âˆ…}, {30 [(decendents, 0)], âˆ…, âˆ…}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
	   // Implementar el mÃ©todo
		
		if(!isEmpty()) { //el arbol es vacío que no etiquete nada		
			this.getLeftBST().tagDecendents();
			this.getRightBST().tagDecendents(); 
			//estoy abajo del todo  en la hoja mas profunda
			
			if(this.getLeftBST().isEmpty() && this.getRightBST().isEmpty()) { //no tiene izda ni derecha
				this.setTag("decendents", 0);
			}
			else if(this.getLeftBST().isEmpty() || this.getRightBST().isEmpty()) {//tiene izda o derecha (las dos juntas nunca porque ya se tiene en cuenta arriba)
				
				if(this.getRightBST().isEmpty()) { //no tiene derecha, tiene izda
					
					this.setTag("decendents", 1+(int)this.getLeftBST().getTag("decendents"));
				}
				else {//no tiene izda, tiene derecha
					this.setTag("decendents", 1+(int)this.getRightBST().getTag("decendents"));
				}
			}
			else { //las dos son ocupadas
				this.setTag("decendents", 2+(int)this.getRightBST().getTag("decendents")+(int)this.getLeftBST().getTag("decendents"));
			}
		
		}
	
	}
		
	
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol por niveles segÃºn 
         * el recorrido en anchura
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * 		{50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los nodos en el orden: 50, 30, 80, 10, 40, 60
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		// puede implementarse creando una lista con el recorrido en anchura de los elementos del Ã¡rbol y devolver el iterador de dicha lista
		ArrayList<BinarySearchTreeImpl<T>> cola = new ArrayList<BinarySearchTreeImpl<T>>();
		ArrayList<T> lista = new ArrayList<T>();

		cola.add(this);//meto la raíz
		
		while(!cola.isEmpty()) {
			BinarySearchTreeImpl<T> aux = cola.get(0);
			lista.add(cola.get(0).getContent());
			cola.remove(0);
			if(!aux.getLeftBST().isEmpty()) {
				cola.add(aux.getLeftBST());
			}
			if(!aux.getRightBST().isEmpty()) {
				cola.add(aux.getRightBST());
			}
		}
		
		Iterator<T> iterador = lista.iterator();
		return iterador;
		
		
		
	}	
	
	

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Calcula y devuelve el nÃºmero de nodos que son hijos Ãºnicos 
	 *  y etiqueta cada nodo que sea hijo Ãºnico (no tenga hermano hijo del mismo padre) 
	 *   con la etiqueta "onlySon" y el valor correspondiente a su posiciÃ³n segÃºn el 
	 *   recorrido inorden en este Ã¡rbol. 
	 *   
	 *   La raÃ­z no se considera hijo Ãºnico.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A", que tiene 3 hijos Ãºnicos, los va etiquetando segÃºn 
	 * su recorrido en inorden. 
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
     *
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], âˆ…, âˆ…}, âˆ…}, 
	 *      {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public int tagOnlySonInorder() {
		int count [] = new int[1];
		count[0] = 0;
		
		if(isEmpty()) {
			
			return count[0];
			
		}
		else {
			return tagOnlySonInorderRec(count);
			
		}
		
	}

	private int tagOnlySonInorderRec(int[] count) {
		
		if(isEmpty()) {
			return 0;
		}else if(this.father == null) {
			this.getLeftBST().tagOnlySonInorderRec(count); //la raiz nunca la marco como hijo unico
			this.getRightBST().tagOnlySonInorderRec(count);
			return count[0];
		}
		else {
			this.getLeftBST().tagOnlySonInorderRec(count);
			
			if(this.father.getLeftBST().isEmpty()) {
				count[0]++;
				this.setTag("onlySon", count[0]);
				
				
			}
			else if(this.father.getRightBST().isEmpty()) {
				
				count[0]++;
				this.setTag("onlySon", count[0]);
				
				
			}
		
			this.getRightBST().tagOnlySonInorderRec(count);
			
			return count[0];
		}	
	}
	
	
	
}

