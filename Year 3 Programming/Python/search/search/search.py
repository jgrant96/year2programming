# search.py
# ---------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

"""
In search.py, you will implement generic search algorithms which are called 
by Pacman agents (in searchAgents.py).
"""

import util

class SearchProblem:
  """
  This class outlines the structure of a search problem, but doesn't implement
  any of the methods (in object-oriented terminology: an abstract class).
  
  You do not need to change anything in this class, ever.
  """
  
  def getStartState(self):
     """
     Returns the start state for the search problem 
     """
     util.raiseNotDefined()
    
  def isGoalState(self, state):
     """
       state: Search state
    
     Returns True if and only if the state is a valid goal state
     """
     util.raiseNotDefined()

  def getSuccessors(self, state):
     """
       state: Search state
     
     For a given state, this should return a list of triples, 
     (successor, action, stepCost), where 'successor' is a 
     successor to the current state, 'action' is the action
     required to get there, and 'stepCost' is the incremental 
     cost of expanding to that successor
     """
     util.raiseNotDefined()

  def getCostOfActions(self, actions):
     """
      actions: A list of actions to take
 
     This method returns the total cost of a particular sequence of actions.  The sequence must
     be composed of legal moves
     """
     util.raiseNotDefined()
           

def tinyMazeSearch(problem):
  """
  Returns a sequence of moves that solves tinyMaze.  For any other
  maze, the sequence of moves will be incorrect, so only use this for tinyMaze
  """
  from game import Directions
  s = Directions.SOUTH
  w = Directions.WEST
  return  [s,s,w,s,w,w,s,w]

def depthFirstSearch(problem):
  """
  Search the deepest nodes in the search tree first [p 85].
  
  Your search algorithm needs to return a list of actions that reaches
  the goal.  Make sure to implement a graph search algorithm [Fig. 3.7].
  
  To get started, you might want to try some of these simple commands to
  understand the search problem that is being passed in:
  
  print "Start:", problem.getStartState()
  print "Is the start a goal?", problem.isGoalState(problem.getStartState())
  print "Start's successors:", problem.getSuccessors(problem.getStartState())
  """
  "*** YOUR CODE HERE ***"
  
  stackList = util.Stack()
  pathStack = []
  visited = []
  visited.append(problem.getStartState())
  for startSucc in problem.getSuccessors(problem.getStartState()):
  	stackList.push((startSucc[0], [startSucc[1]]))
  	
  while stackList.isEmpty() == False:
  	nodeToNode = stackList.pop()
  	pathStack.append(nodeToNode)
  	
  	if problem.isGoalState(nodeToNode[0]):
  		return nodeToNode[1]
  	if nodeToNode[0] not in visited:
		visited.append(nodeToNode[0])
		succList = problem.getSuccessors(nodeToNode[0])
		for succ in problem.getSuccessors(nodeToNode[0]):
			if succ[0] not in visited:
				path = []
				for x in nodeToNode[1]:
					path.append(x)
				path.append(succ[1])
				stackList.push(( succ[0], path))
  
  return []

  util.raiseNotDefined()

def breadthFirstSearch(problem):
  "Search the shallowest nodes in the search tree first. [p 81]"
  "*** YOUR CODE HERE ***"
  
  queueList = util.Queue()
  pathStack = []
  visited = []
  visited.append(problem.getStartState())
  for startSucc in problem.getSuccessors(problem.getStartState()):
  	queueList.push((startSucc[0], [startSucc[1]]))
  	
  while queueList.isEmpty() == False:
  	nodeToNode = queueList.pop()
  	pathStack.append(nodeToNode)
  	
  	if problem.isGoalState(nodeToNode[0]):
  		return nodeToNode[1]
  	if nodeToNode[0] not in visited:
		visited.append(nodeToNode[0])
		succList = problem.getSuccessors(nodeToNode[0])
		for succ in problem.getSuccessors(nodeToNode[0]):
			if succ[0] not in visited:
				path = []
				for x in nodeToNode[1]:
					path.append(x)
				path.append(succ[1])
				queueList.push(( succ[0], path))
  
  return []
  util.raiseNotDefined()
	
      
def uniformCostSearch(problem):
  "Search the node of least total cost first. "
  "*** YOUR CODE HERE ***"
  queueList = util.PriorityQueue()
  pathStack = []
  visited = []
  visited.append(problem.getStartState())
  for startSucc in problem.getSuccessors(problem.getStartState()):
  	queueList.push((startSucc[0], [startSucc[1]], startSucc[2]), startSucc[2])
  	
  while queueList.isEmpty() == False:
  	nodeToNode = queueList.pop()
  	pathStack.append(nodeToNode)

  	
  	if problem.isGoalState(nodeToNode[0]):
  		return nodeToNode[1]
  	if nodeToNode[0] not in visited:
		visited.append(nodeToNode[0])
		for succ in problem.getSuccessors(nodeToNode[0]):
			if succ[0] not in visited:
				path = []
				for x in nodeToNode[1]:
					path.append(x)
				path.append(succ[1])
				queueList.push((succ[0], path, (nodeToNode[2] + succ[2])), (nodeToNode[2] + succ[2]))
			elif succ[0] in visited:
       		 		newList = []
				while (queueList.isEmpty() == False):
					nodes = queueList.pop()
					if ((succ[0] == nodes[0]) and ((succ[2] + nodeToNode[2]) < nodes[2])):
						path2 = []
						for y in nodeToNode[1]:
							path2.append(y)
						path2.append(succ[1])
						newList.append(succ[0], path2, (succ[2] + nodeToNode[2]))
						pathStack.remove(nodes)
					else:
						newList.append(nodes)	
				for entries in newList:
					queueList.push((entries[0], entries[1], entries[2]), entries[2])			
						
  return []
  util.raiseNotDefined()

def nullHeuristic(state, problem=None):
  """
  A heuristic function estimates the cost from the current state to the nearest
  goal in the provided SearchProblem.  This heuristic is trivial.
  """
  return 0

def aStarSearch(problem, heuristic=nullHeuristic):
  "Search the node that has the lowest combined cost and heuristic first."
  "*** YOUR CODE HERE ***"
  queueList = util.PriorityQueue()
  pathStack = []
  visited = []
  visited.append(problem.getStartState())
  for startSucc in problem.getSuccessors(problem.getStartState()):
  	queueList.push((startSucc[0], [startSucc[1]], (startSucc[2] + heuristic(startSucc[0], problem))), (startSucc[2] + heuristic(startSucc[0], problem)))
  	
  while queueList.isEmpty() == False:
  	nodeToNode = queueList.pop()
  	pathStack.append(nodeToNode)

  	
  	if problem.isGoalState(nodeToNode[0]):
  		return nodeToNode[1]
  	if nodeToNode[0] not in visited:
		visited.append(nodeToNode[0])
		for succ in problem.getSuccessors(nodeToNode[0]):
			if succ[0] not in visited:
				path = []
				for x in nodeToNode[1]:
					path.append(x)
				path.append(succ[1])
				queueList.push((succ[0], path, (nodeToNode[2] + succ[2] + heuristic(succ[0], problem))), (nodeToNode[2] + succ[2] + heuristic(succ[0], problem)))
			elif succ[0] in visited:
       		 		newList = []
				while (queueList.isEmpty() == False):
					nodes = queueList.pop()
					if ((succ[0] == nodes[0]) and ((succ[2] + nodeToNode[2]+ heuristic(succ[0], problem)) < nodes[2])):
						path2 = []
						for y in nodeToNode[1]:
							path2.append(y)
						path2.append(succ[1])
						newList.append(succ[0], path2, (succ[2] + nodeToNode[2] + heuristic(succ[0], problem)))
						pathStack.remove(nodes)
					else:
						newList.append(nodes)	
				for entries in newList:
					queueList.push((entries[0], entries[1], entries[2]), entries[2])			
						
  return []
  util.raiseNotDefined()
    
  
# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
