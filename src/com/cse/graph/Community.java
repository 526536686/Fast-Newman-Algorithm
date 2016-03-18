package com.cse.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表示图的类
 * 
 * @author 作者 E-mail:
 * @date 创建时间： 2016-3-17 下午2:54:42
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Community {
	int nodeNum = 0; // 表示图中总共有多少个节点
	List<Edge> edgeList = new ArrayList<Edge>(); // 表示图中所有边的列表
	List<Node> nodeList = new ArrayList<Node>(); // 表示图中所有节点的列表

	public int getNodeNum() {
		this.nodeNum = nodeList.size();
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public Community(int n) {
		nodeNum = n;
	}

	/**
	 * 往图中添加一条边
	 * 
	 * @param e
	 */
	public void insertEdge(int i, int j, double weight) {
		Edge edge = new Edge(i, j, weight);
		// 将边加入图中
		edgeList.add(edge);

		Node nodei = new Node();

		Map<Integer, Double> nodeiMap = new HashMap<Integer, Double>();
		nodeiMap.put(j, weight);

		// 判断是接在以后节点邻居后面，还是新建节点
		Node tmp_i = null;
		tmp_i = getNodeById(i);
		if (null == tmp_i) {
			nodei.setId(i);
			nodei.setNeiborNodeId(nodeiMap);
			nodeList.add(nodei);
		} else {
			tmp_i.getNeiborNodeId().putAll(nodeiMap);
		}

		Node nodej = new Node();

		Map<Integer, Double> nodejMap = new HashMap<Integer, Double>();
		nodejMap.put(i, weight);

		// 判断是接在以后节点邻居后面，还是新建节点
		Node tmp_j = null;
		tmp_j = getNodeById(j);
		if (null == tmp_j) {
			nodej.setId(j);
			nodej.setNeiborNodeId(nodejMap);
			nodeList.add(nodej);
		} else {
			tmp_j.getNeiborNodeId().putAll(nodejMap);
		}
	}

	/**
	 * 根据用户输入的边的两个顶点的ID获取该边上的权重
	 * 
	 * @param headId
	 * @param tailId
	 * @return
	 */
	public double getWeight(int headId, int tailId) {
		for (Edge edge : edgeList) {
			int h = edge.getHead();
			int t = edge.getTail();
			// 如果找到该边
			if (headId == h && tailId == t) {
				return edge.getWeight();
			}
		}
		return Double.MIN_VALUE;
	}

	/**
	 * 根据用户指定的NodeId获取Node
	 * 
	 * @param id
	 * @return
	 */
	public Node getNodeById(int id) {
		for (Node node : nodeList) {
			int nodeId = node.getId();
			if (id == nodeId) {
				return node;
			} else {
				continue;
			}
		}
		// 未发现指定Id的节点
		return null;
	}

	public static void main(String[] args) {
		Community community = new Community(1);

		community.insertEdge(1, 2, 0.5);
		community.insertEdge(1, 3, 0.5);
		community.insertEdge(1, 4, 0.5);

		System.out.println(community.getNodeList());
	}
}
