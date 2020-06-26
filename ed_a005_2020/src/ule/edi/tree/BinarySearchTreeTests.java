package ule.edi.tree;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	
	@Before
	public void setupBSTs() {
		
			
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
	    	}

	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
		@Test
		public void testTagDecendentsEjemplo() {
			ejemplo.tagDecendents();
			ejemplo.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",ejemplo.toString());
		}
		
		@Test
		public void testTagHeightEjemplo() {
			other.tagHeight();
			other.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",other.toString());
		}
		
		
		@Test
		public void testTagOnlySonEjemplo() {
		
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3,other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",other.toString());

		}
		
		@Test
		public void insertSeveralNullTest() {
			BinarySearchTreeImpl<Integer> nuevo = new BinarySearchTreeImpl<Integer>();


			nuevo.insert(3,null,5);
			Assert.assertEquals(nuevo.toString(), "∅");

		}
		
		@Test
		public void insertAlreadyTest() {
			
			Assert.assertFalse(ejemplo.insert(10));

			
		}
		
		@Test
		public void containsTest() {
			
			//arbol vacio
			BinarySearchTreeImpl<Integer> nuevo = new BinarySearchTreeImpl<Integer>();
			Assert.assertFalse(nuevo.contains(10));
			
			Assert.assertTrue(ejemplo.contains(10));
			//rama mayor
			Assert.assertTrue(ejemplo.contains(30));
			//rama menor
			Assert.assertTrue(ejemplo.contains(5));
			
			//null
			Assert.assertFalse(ejemplo.contains(null));

		}
		
		@Test
		public void removeSeveralTest() {
			
			ejemplo.remove(2,30);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, ∅, ∅}, {20, {15, ∅, ∅}, ∅}}");
			
			
		}
		
		@Test
		public void removeSeveralNullTest() {
			
			ejemplo.remove(2,null);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
			
			
		}
		
		@Test
		public void removeSeveralNotContainedTest() {
			
			ejemplo.remove(2,70);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
			
			
		}
		
		@Test
		public void remove1RightChildTest() {
			BinarySearchTreeImpl<Integer> nuevo = new BinarySearchTreeImpl<Integer>();


			nuevo.insert(3,2,5,6);
			Assert.assertEquals(nuevo.toString(), "{3, {2, ∅, ∅}, {5, ∅, {6, ∅, ∅}}}");

			nuevo.remove(5);
			Assert.assertEquals(nuevo.toString(), "{3, {2, ∅, ∅}, {6, ∅, ∅}}");

			
		}

		
		@Test
		public void removeMinorOfTheHighestTest() {

			//modifico el arbol de ejemplo para que no sea la primear izda que tome

			ejemplo.insert(35);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {35, ∅, ∅}}}}");
			
			
			ejemplo.remove(20);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {30, {15, ∅, ∅}, {35, ∅, ∅}}}");

			
		}
		
		@Test
		public void tagDecendentsTest() {
			
			ejemplo.insert(35);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {35, ∅, ∅}}}}");
			ejemplo.remove(15);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, {35, ∅, ∅}}}}");

			
			ejemplo.tagDecendents();
			ejemplo.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], ∅, {30 [(decendents, 1)], ∅, {35 [(decendents, 0)], ∅, ∅}}}}",ejemplo.toString());
		}

		@Test
		public void iteratorTest() {
			
			Iterator<Integer> iterador = ejemplo.iteratorWidth();
	
				
				Assert.assertEquals(iterador.next().toString(), "10");
				Assert.assertEquals(iterador.next().toString(), "5");
				Assert.assertEquals(iterador.next().toString(), "20");
				Assert.assertEquals(iterador.next().toString(), "2");
				Assert.assertEquals(iterador.next().toString(), "15");
				Assert.assertEquals(iterador.next().toString(), "30");


		}
	
		
		@Test
		public void insertColectionTest() {
			ArrayList<Integer> collection = new ArrayList<Integer>();
			collection.add(77);
			collection.add(88);
			collection.add(0);
			
			Assert.assertEquals(ejemplo.insert(collection), 3);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, {0, ∅, ∅}, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {77, ∅, {88, ∅, ∅}}}}}");


		}
		
		@Test
		public void insertColectionNullTest() {
			ArrayList<Integer> collection = new ArrayList<Integer>();
			collection.add(77);
			collection.add(null);
			collection.add(0);
			
			Assert.assertEquals(ejemplo.insert(collection), 0);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");


		}
		
		@Test
		public void insertColectionAlreadyTest() {
			ArrayList<Integer> collection = new ArrayList<Integer>();
			collection.add(77);
			collection.add(77);
			collection.add(0);
			
			Assert.assertEquals(ejemplo.insert(collection), 2);
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, {0, ∅, ∅}, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, {77, ∅, ∅}}}}");


		}
		
		@Test
		public void tagOnlySonInorderEmptyTreeTest() {
			//arbol vacio
			BinarySearchTreeImpl<Integer> nuevo = new BinarySearchTreeImpl<Integer>();
			Assert.assertEquals(nuevo.tagOnlySonInorder(), 0);

		}
		
		@Test
		public void tagOnlySonInorderEmptyLeftTest() {
			ejemplo.remove(15);
			Assert.assertEquals(ejemplo.tagOnlySonInorder(), 2);

		}
		
	/*	@Test(expected = IllegalArgumentException.class)
		public void insertNullTest() {
			ejemplo.insert(null);
		}
	*///   AMBIGUEDAD POR SOBRECARGA DE METODO
		
		@Test(expected = NoSuchElementException.class)
		public void removeNotFoundTest() {
			ejemplo.remove(200);
		}
		
	}


