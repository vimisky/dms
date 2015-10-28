package com.vimisky.alg;

import java.util.ArrayList;
import java.util.List;

public class GenericBinTree <E extends Comparable<? super E>>{

	private GenericBinNode<E> rootNode;
	
	public GenericBinTree(){
		this.rootNode = null;
	}
	
	public GenericBinTree(List<BinNode> nodeList){
		
	}
	
	/**
	 * 嵌套类
	 * */
	private static class GenericBinNode<E>{
		protected E element;
		protected GenericBinNode<E> leftNode;
		protected GenericBinNode<E> rightNode;
		protected int height;
		protected int depth;
	}
	/**
	 * 最大最小节点组合
	 * */
	private static class MaxMinComp<E>{
		protected GenericBinNode<E> maxNode;
		protected GenericBinNode<E> minNode;
		protected int distance;
	}
	
	/**
	 * @return the rootNode
	 */
	public GenericBinNode<E> getRootNode() {
		return this.rootNode;
	}

	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(GenericBinNode<E> rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * 初始化二叉树
	 * */
	public void initialTree(List<E> nodeList){
		if (nodeList == null || nodeList.size() == 0) {
			return;
		}
		for (int i = 0; i < nodeList.size(); i++) {
			this.rootNode = insert(nodeList.get(i), this.rootNode);
		}
	}
	
	
	/**
	 * 先序遍历
	 * */
	public void preOrder(GenericBinNode<E> node){
		if (node != null) {
			visit(node);
			preOrder(node.leftNode);
			preOrder(node.rightNode);			
		}
	}
	
	/**
	 * 中序遍历
	 * **/
	public void inOrder(GenericBinNode<E> node){
		if (node != null) {
			inOrder(node.leftNode);
			visit(node);
			inOrder(node.rightNode);			
		}
	}
	
	/**
	 * 后序遍历
	 * */
	public void postOrder(GenericBinNode<E> node){
		if (node != null) {
			postOrder(node.leftNode);
			postOrder(node.rightNode);
			visit(node);

		}
	}
	/**
	 * 访问节点
	 * */
	public void visit(GenericBinNode<E> node){
		if (node == null) {
			return;
		}
		System.out.print(node.element+"\t");
	}
	/**
	 * 插入
	 * */
	public GenericBinNode<E> insert(E element,GenericBinNode<E> node){
		if (node == null) {
			node = new GenericBinNode<E>();
			node.element = element;
		}else {
			if (element.compareTo(node.element)>0) {
				node.rightNode = insert(element, node.rightNode);
			}else {
				node.leftNode = insert(element, node.leftNode);
			}
		}
		
		return node;
	}
	/**
	 * 删除
	 * */
	public void delete(BinNode node){
		
	}
	/**
	 * 查询
	 * */
	public GenericBinNode<E> find(E element, GenericBinNode<E> node){
		GenericBinNode<E> targetNode = null;
		if (node == null) {
			return null;
		}
		int result = element.compareTo(node.element);
		if (result>0) {
			targetNode = find(element, node.rightNode);
		}
		if (result<0) {
			targetNode = find(element,node.leftNode);
		}
		if (result == 0) {
			targetNode = new GenericBinNode<E>();
			targetNode.element = element;
		}
		return targetNode;
	}
	/**
	 * 查询最小
	 * */
	public GenericBinNode<E> findMin(GenericBinNode<E> node){
		GenericBinNode<E> targetNode = null;
		if (node == null) {
			return null;
		}else {
			if (node.leftNode != null) {
				targetNode = findMin(node.leftNode);
			}else {
				targetNode = node;
			}
		}
		
		return targetNode;
	}
	/**
	 * 查询最大
	 * */
	public GenericBinNode<E> findMax(GenericBinNode<E> node){
		GenericBinNode<E> targetNode = null;
		while(node.rightNode!=null) {
			targetNode = node.rightNode;
			node = node.rightNode;
		}
		return targetNode;
	}
	/**
	 * 树的高度
	 * */
	public int height(){
		return 0;
	}
	/**
	 * 节点的高度
	 * */
	public int height(BinNode node){
		return 0;
	}
	/**
	 * 树的深度
	 * */
	public int depth(){
		
		return rootNode == null? 0 : depth(rootNode);
	}
	/**
	 * 节点的深度
	 * */
	public int depth(GenericBinNode<E> node){
		int leftDepth,rightDepth;
		if (node == null) {
			return 0;
		}
		leftDepth = depth(node.leftNode);
		rightDepth = depth(node.rightNode);
		return leftDepth>rightDepth?leftDepth+1:rightDepth+1;
	}
	/**
	 * 树的叶子数量
	 * */
	public int numOfleaf(GenericBinNode<E> node){
		if (node == null) {
			return 0;
		}
		if (node.leftNode == null && node.rightNode == null) {
			return 1;
		}
		
		return (node.leftNode == null?0:numOfleaf(node.leftNode))+(node.rightNode == null ? 0 : numOfleaf(node.rightNode));
	}
	/**
	 * 寻找一条路径的最大最小节点
	 * */
	public void findMaxMinComp(List<MaxMinComp<E>> lists, MaxMinComp<E> maxMinComp,GenericBinNode<E> node){
		
		if (node == null) {
			return ;
		}

		if (node.element.compareTo(maxMinComp.minNode.element)<0) {
			maxMinComp.minNode = node;
		}
		if (node.element.compareTo(maxMinComp.maxNode.element)>0) {
			maxMinComp.maxNode = node;
		}
		if (node.leftNode == null && node.rightNode == null) {
			lists.add(maxMinComp);
		}
		MaxMinComp<E> leftMaxMinComp = new MaxMinComp<E>();
		MaxMinComp<E> rightMaxMinComp = new MaxMinComp<E>();
		leftMaxMinComp.minNode = maxMinComp.minNode;
		leftMaxMinComp.maxNode = maxMinComp.maxNode;
		rightMaxMinComp.minNode = maxMinComp.minNode;
		rightMaxMinComp.maxNode = maxMinComp.maxNode;
		findMaxMinComp(lists, leftMaxMinComp, node.leftNode);
		findMaxMinComp(lists, rightMaxMinComp, node.rightNode);
	}
	
	/**
	 * 计算amplitude
	 * */
	public int amplitude(){
		List<MaxMinComp<E>> lists = new ArrayList<GenericBinTree.MaxMinComp<E>>();
		MaxMinComp<E> maxMinComp = new MaxMinComp<E>();
		maxMinComp.minNode = this.getRootNode();
		maxMinComp.maxNode = this.getRootNode();
		findMaxMinComp(lists, maxMinComp, this.getRootNode());
		int amplitude = 0;
		for (MaxMinComp<E> tmpMaxMinComp : lists) {
			System.out.println("distance is "+tmpMaxMinComp.maxNode.element+"  to " + tmpMaxMinComp.minNode.element);
			tmpMaxMinComp.distance = Integer.parseInt(tmpMaxMinComp.maxNode.element.toString())  - Integer.parseInt(tmpMaxMinComp.minNode.element.toString());
			if (tmpMaxMinComp.distance >amplitude) {
				amplitude = tmpMaxMinComp.distance;
			}
		}
		return amplitude;
	}
	/**
	 *共同祖先
	 * **/
	public GenericBinNode<E> commonAncestor(GenericBinNode<E> node1,GenericBinNode<E> node2){
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array = {60,20,120,10,40,100,180,30,50,80,110};
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		GenericBinTree<Integer> gBinTree = new GenericBinTree<Integer>();
		gBinTree.initialTree(list);
		gBinTree.preOrder(gBinTree.getRootNode());
		System.out.println();
		gBinTree.inOrder(gBinTree.getRootNode());
		System.out.println();
		gBinTree.postOrder(gBinTree.getRootNode());
		System.out.println();
		GenericBinNode<Integer> targetNode = gBinTree.findMin(gBinTree.getRootNode());
		System.out.println(targetNode.element);
		targetNode = gBinTree.findMax(gBinTree.getRootNode());
		System.out.println(targetNode.element);
		System.out.println("Tree leaves : "+ gBinTree.numOfleaf(gBinTree.getRootNode()));
		System.out.println("Tree Depth : "+ gBinTree.depth(gBinTree.getRootNode()));
		System.out.println("Tree amplitude : "+gBinTree.amplitude());
	}

}
