package com.cse.graph;

/**
 * 图中表示边的类
 * 
 * @author 作者 E-mail:
 * @date 创建时间： 2016-3-17 下午2:49:49
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Edge {
	int head; // 边的头节点ID
	int tail; // 边的尾节点ID
	double weight; // 边的权重

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * 边的默认构造函数
	 */
	Edge() {
		head = -1;
		tail = -1;
		weight = 0;
	}

	Edge(int i, int j, double weight) {
		head = i;
		tail = j;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return head + "--" + tail + ": " + weight;
	}
}
