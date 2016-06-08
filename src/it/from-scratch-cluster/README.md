# "From Scratch Cluster" Integration Test

This test runs the following commands and verifies their result 
against a 3 node Cassandra Cluster:

1. Check that if the test keyspace exists
  # Asserts that this is false
2. Create test keyspace
3. Check that test keyspace exists
  # Assert that this is true
4. Check if the migrations setup was run on the test keyspace
  # Assert that this is false
5. Run the migrations setup
6. Check if the migrations setup was run on the test keyspace
  # Assert that this is true
