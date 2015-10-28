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
	 * ��ʼ��������
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
	 * �������
	 * */
	public void preOrder(BinNode node){
		if (node != null) {
			System.out.print(node.getProperty()+" // ");
			preOrder(node.getLeftNode());
			preOrder(node.getRightNode());			
		}

	}
	
	/**
	 * �������
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
	 * �������
	 * */
	public void postOrder(BinNode node){
		if (node != null) {
			postOrder(node.getLeftNode());
			postOrder(node.getRightNode());
			System.out.print(node.getProperty()+" // ");

		}
	}
	/**
	 * ����
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
	 * ɾ��
	 * */
	public void delete(BinNode node){
		
	}
	/**
	 * ��ѯ
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
	 * ��ѯ��С
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
	 * ��ѯ���
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
	 * ���ĸ߶�
	 * */
	public int height(){
		return 0;
	}
	/**
	 * �ڵ�ĸ߶�
	 * */
	public int height(BinNode node){
		return 0;
	}
	/**
	 * �������
	 * */
	public int deep(){
		return 0;
	}
	/**
	 * �ڵ�����
	 * */
	public int deep(BinNode node){
		return 0;
	}
	/**
	 * ����Ҷ������
	 * */
	public int numOfleaf(){
		return 0;
	}
	/**
	 * ����amplitude
	 * */
	public int amplitude(){
		
		return 0;
	}
	/**
	 *��ͬ����
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
//		���ҽڵ�
		BinNode targetNode = binTree.find(100, binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}
//		������С�ڵ�
		targetNode = binTree.findMin(binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}		
//		�������ڵ�
		targetNode = binTree.findMax(binTree.getRootNode());
		if (targetNode != null) {
			System.out.println("Target Node found , Value is "+targetNode.getProperty());	
		}else {
			System.out.println("Target Not in Tree");
		}				
	}

}
