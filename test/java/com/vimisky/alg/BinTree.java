package com.vimisky.alg;

import java.util.ArrayList;
import java.util.List;

public class BinTree{

	private BinNode rootNode;
	
	public BinTree(){
		this.rootNode = null;
	}
	
	public BinTree(List<BinNode> nodeList){
		
	}
	
	
	
	
	/**
	 * @return the rootNode
	 */
	public BinNode getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(BinNode rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * 初始化二叉树
	 * */
	public void initialTree(List<Integer> nodeList){
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
	public void preOrder(BinNode node){
		if (node != null) {
			System.out.print(node.getProperty()+" // ");
			preOrder(node.getLeftNode());
			preOrder(node.getRightNode());			
		}

	}
	
	/**
	 * 中序遍历
	 * **/
	public void inOrder(BinNode node){
		if (node != null) {
			
			inOrder(node.getLeftNode());
			System.out.print(node.getProperty()+" // ");
//			System.out.print((char)node.getProperty());

			inOrder(node.getRightNode());			
		}
	}
	
	/**
	 * 后序遍历
	 * */
	public void postOrder(BinNode node){
		if (node != null) {
			postOrder(node.getLeftNode());
			postOrder(node.getRightNode());
			System.out.print(node.getProperty()+" // ");

		}
	}
	/**
	 * 插入
	 * */
	public BinNode insert(int data,BinNode node){
		if (node == null) {
			node = new BinNode();
			node.setProperty(data);
		}else {
			if (data > node.getProperty()) {
				node.setRightNode(insert(data, node.getRightNode()));
			}else {
				node.setLeftNode(insert(data, node.getLeftNode()));
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
	public BinNode find(int data, BinNode node){
		BinNode targetNode = null;
		if (node == null) {
			return null;
		}
		if (data>node.getProperty()) {
			targetNode = find(data, node.getRightNode());
		}
		if (data<node.getProperty()) {
			targetNode = find(data, node.getLeftNode());
		}
		if (data == node.getProperty()) {
			targetNode = new BinNode();
			targetNode.setProperty(data);
		}
		return targetNode;
	}
	/**
	 * 查询最小
	 * */
	public BinNode findMin(BinNode node){
		BinNode targetNode = null;
		if (node == null) {
			return null;
		}else {
			if (node.getLeftNode() != null) {
				targetNode = findMin(node.getLeftNode());
			}else {
				targetNode = node;
			}
		}
		
		return targetNode;
	}
	/**
	 * 查询最大
	 * */
	public BinNode findMax(BinNode node){
		BinNode targetNode = null;
		while(node.getRightNode()!=null) {
			targetNode = node.getRightNode();
			node = node.getRightNode();
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
	public int deep(){
		return 0;
	}
	/**
	 * 节点的深度
	 * */
	public int deep(BinNode node){
		return 0;
	}
	/**
	 * 树的叶子数量
	 * */
	public int numOfleaf(){
		return 0;
	}
	/**
	 * 计算amplitude
	 * */
	public int amplitude(){
		
		return 0;
	}
	/**
	 *共同祖先
	 * **/
	public BinNode commonAncestor(BinNode node1,BinNode node2){
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {60,20,120,10,40,100,180,30,50,80,110};
//		int[] array = {'+','+','*','a','*','+','g','b','c','*','f','d','e'};

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
//			System.out.println("char array:"+(char)array[i]);
		}
		BinTree binTree = new BinTree();
		binTree.initialTree(list);
		System.out.println("test: root: "+ binTree.getRootNode().getProperty());
		System.out.println("pre:");
		binTree.preOrder(binTree.getRootNode());
		System.out.println("\n"+"Middle:");
		binTree.inOrder(binTree.getRootNode());
		System.out.println("\n"+"post:");
		binTree.postOrder(binTree.getRootNode());
		System.out.println();
//		查找节点
		BinNode targetNode = binTree.find(100, binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}
//		查找最小节点
		targetNode = binTree.findMin(binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}		
//		查找最大节点
		targetNode = binTree.findMax(binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}				
	}

}
