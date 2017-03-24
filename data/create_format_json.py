import pandas as pd
import numpy as np

def create_json_index(id):
	"""
	"""
	return '{"index":{"_id":"'+str(id)+'"} }'
def create_format_from_file(file):
	line = 0
	while True:
		data = pd.read_csv(filepath_or_buffer=file,
				sep="\n",skiprows=line, nrows = 100)
		line+=100
		print data.size
		if data.size == 0:
			break
	return data
def create_format(list_file):
	for file in list_file:
		pass
if __name__ == '__main__':
	create_format_from_file("test.json")