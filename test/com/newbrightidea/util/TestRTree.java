package com.newbrightidea.util;

import java.util.List;

import org.junit.Test;

public class TestRTree
{
  private static final double[] ZEROES = { 0.0, 0.0 };
  private static final double[] ONES = {1.0, 1.0};

  @Test
  public void testCreation()
  {
    RTree<Long> rt = new RTree<Long>();
  }
  
  @Test
  public void testInsertWithPoint()
  {
    RTree<Object> rt = new RTree<Object>();
    Object o = new Object();
    rt.insert(ZEROES, ZEROES, o);
    List<Object> results =
      rt.search(new double[] {-1.0, -1.0},
                new double[] { 2.0,  2.0});
    assert(results.get(0) == o);
  }
  
  @Test
  public void testInsertWithRect()
  {
    RTree<Object> rt = new RTree<Object>();
    Object o = new Object();
    rt.insert(ZEROES, ONES, o);
    List<Object> results =
      rt.search( new double[] {-1.0, -1.0},
                 new double[] {3.0, 3.0} );
    assert(results.get(0) == o);
  }
  
  @Test
  public void testEmptyResults()
  {
    RTree<Object> rt = new RTree<Object>();
    Object o = new Object();
    rt.insert(ZEROES, ZEROES, o);
    List<Object> results =
      rt.search(new double[] {-1.0, -1.0},
                new double[] { 0.5,  0.5});
    assert(results.isEmpty());
  }
  
  @Test
  public void testSplitNodes()
  {
    RTree<Object> rt = new RTree<Object>(50,2,2);
    int numEntries = rt.getMaxEntries() * 4;
    double[] coords = new double[] { 0.0, 0.0 };
    double[] dims = new double[] { 0.5, 0.5 };
    Object[] entries = new Object[numEntries];
    
    for ( int i = 0; i < numEntries; i++ )
    {
      coords[0] = i;
      entries[i] = new Object();
      rt.insert(coords, dims, entries[i]);
    }
    
    for ( int i = 0; i < numEntries; i++ )
    {
      coords[0] = i;
      List<Object> results = rt.search(coords, dims);
      assert(results.get(0) == entries[i]);
    }
  }
  
  @Test
  public void testDelete()
  {
    RTree<Object> rt = new RTree<Object>();
    Object entry = new Object();
    List<Object> results;
    rt.insert(ZEROES, ONES, entry);
    results = rt.search(ZEROES, ONES);
    assert(results.size() == 1);
    rt.delete(ZEROES, ONES, entry);
    results = rt.search(ZEROES, ONES);
    assert(results.isEmpty());
  }
}
