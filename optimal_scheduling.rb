# Idea: Receive an array of jobs with start times and end times.
# Write an algorithmn that finds a way to fit in the most jobs based on these intervals
# This problem was inspired by The Algorithmn and Design Manual

# A vertex class is used to keep track of sets, and any overlap. Edges are handled inside the Vertex class, rather than as a seperate set
class Vertex
  attr_reader :start, :end
  attr_accessor :connections
  def initialize(interval)
    @start = interval[0]
    @end = interval[1]
    @connections = []
  end

  def add_to_set(vertex2)
    @connections.push(vertex2)
    vertex2.connections.push(self)
  end

end

def optimal_scheduling(array)
  sets = combine_overlapping_sets(array)
  jobs = []
  until sets.empty?
    set = find_earliest_completion_date(sets)
    # Find the set that completes the earliest, then delete it, as well as
    # any other sets connected to it from the remaining sets.
    jobs.push([set.start, set.end])
    connections = set.connections
    connections.each do |vertex|
      sets.delete(vertex)
    end
    sets.delete(set)
  end
  jobs
end

def find_earliest_completion_date(sets)
  minimum = 0
  sets.each_with_index do |vertex, index|
    if vertex.end < sets[minimum].end
      minimum = index
    end
  end
  sets[minimum]
end

def combine_overlapping_sets(array)
  vertexes = []
  array.each do |set|
    idx = 0
    new_vertex = Vertex.new(set)
    while idx < vertexes.length
      vertex = vertexes[idx]
      # this set begins within the start and end of this vertex
      if set[0] >= vertex.start && set[0] <= vertex.end
        vertex.add_to_set(new_vertex)
      # this set starts before the vertex, but the vertex begins before the set ends
      elsif set[0] <= vertex.start && set[1] >= vertex.start
        vertex.add_to_set(new_vertex)
      end
      idx += 1
    end
    vertexes.push(new_vertex)
  end
  vertexes
end

array = [[0,3], [1,4], [1,2], [3,4], [5,9], [10,12], [8,10], [6,7], [11,12]]
print optimal_scheduling(array)
# Expected output: [1,2], [3,4], [6,7], [8,10], [11,12]
