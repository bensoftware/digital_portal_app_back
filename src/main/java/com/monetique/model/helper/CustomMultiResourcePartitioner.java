package com.monetique.model.helper;
/*
 * Copyright 2006-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class CustomMultiResourcePartitioner implements Partitioner {

    private static final String DEFAULT_KEY_NAME = "fileName";

    private static final String PARTITION_KEY = "partition";

    private List<Path> resources = null;

    private String keyName = DEFAULT_KEY_NAME;
    
    
    

    public CustomMultiResourcePartitioner(List<Path> resources) {
		super();
		this.resources = resources;
	}
	public CustomMultiResourcePartitioner() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
     * The resources to assign to each partition. In Spring configuration you
     * can use a pattern to select multiple resources.
     * @param resources the resources to use
     */
    
    public void setResources(List<Path> resources) {
        this.resources = resources;
    }
    /**
     * The name of the key for the file name in each {@link ExecutionContext}.
     * Defaults to "fileName".
     * @param keyName the value of the key
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Assign the filename of each of the injected resources to an
     * {@link ExecutionContext}.
     *
     * @see Partitioner#partition(int)
     */
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
    	
        Map<String, ExecutionContext> map = new HashMap<String, ExecutionContext>(gridSize);
        int i = 0;
	    for(Path file : resources){
	    				  
	    	  if(Files.isRegularFile(file)) {
	    		  ExecutionContext context = new ExecutionContext();
	    		  //System.out.println(file.getFileName().toString());
	              context.putString(keyName, file.getFileName().toString());
	              map.put(PARTITION_KEY + i, context);
	              i++;
	    	  }
	    }
      
        return map;
    }

}
