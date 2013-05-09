#include <iostream>
#include <map>
#include <stdexcept>
#include <string>


class MeshGeometry;

template <class T>
class ResourceManager {

typedef std::map<std::string, T*> ResourceMap;

public:

    virtual T * Load(const std::string &) = 0;
    virtual void Free(const std::string &) = 0;

    T * Get(const std::string & name) {
		typename ResourceMap::iterator it = resources.find( name );

		if(it != resources.end()) {
			return it->second;
		}

		return NULL;
	}

protected:

	ResourceManager(void) {} 
    ~ResourceManager();

    void Register(const std::string & name, T* resource);
    void UnRegister(const std::string & name);

private:

    std::map <std::string, T *> resources;
};


typedef unsigned char BYTE;

// TODO
class Image {
public:

	Image(int width, int height, BYTE *data) {
	}

	~Image(void) {
	}
};

// Singleton class
class ImageManager : public ResourceManager<Image> {

public :

	static ImageManager* Instance();

    Image * Load(const std::string & path);
	
	void Free(const std::string & name);

private :

	ImageManager() {};									// private so that it can  not be called
	ImageManager(ImageManager const&) {};				// copy constructor is private
	ImageManager& operator=(ImageManager const&) {};	// assignment operator is private

	static ImageManager* m_pInstance;
};

// Singleton class
class ModelManager : public ResourceManager<MeshGeometry> {

public:

	static ModelManager* Instance();

	MeshGeometry * Load(const std::string & path);
	MeshGeometry * LoadTerrain(const std::string & path);

    void Free(const std::string & name);

private:

	ModelManager() {};									// private so that it can  not be called
	ModelManager(ModelManager const&) {};				// copy constructor is private
	ModelManager& operator=(ModelManager const&) {};	// assignment operator is private

	static ModelManager* m_pInstance;
};


